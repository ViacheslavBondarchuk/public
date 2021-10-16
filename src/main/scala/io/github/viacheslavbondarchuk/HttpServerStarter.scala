package io.github.viacheslavbondarchuk

import akka.actor.typed.scaladsl.{ActorContext, Behaviors}
import akka.actor.typed.{ActorRef, ActorSystem, Behavior}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.{Http, ServerBuilder}
import io.github.viacheslavbondarchuk.command.Command
import io.github.viacheslavbondarchuk.registry.PersonRegistry
import io.github.viacheslavbondarchuk.route.{GlobalRouteConcatenation, PersonRoute}
import io.github.viacheslavbondarchuk.service.impl.PersonAskServiceImpl

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

case object HttpServerStarter {
  def start(host: String, port: Integer, serverName: String): Unit = ActorSystem[Nothing](createServer(host, port), serverName)

  private def createGlobalRoute(actorContext: ActorContext[Nothing], actorSystem: ActorSystem[Nothing]): Seq[Route] = {
    val personRegistryActor: ActorRef[Command] = actorContext.spawn(PersonRegistry(), "personActor")

    Seq(PersonRoute.createRoute(new PersonAskServiceImpl(personRegistryActor, actorSystem)))
  }

  private def createServer(host: String, port: Integer)(implicit ec: ExecutionContext = ExecutionContext.global): Behavior[Nothing] = Behaviors.setup[Nothing] {
    actorContext: ActorContext[Nothing] => {
      implicit val actorSystem: ActorSystem[Nothing] = actorContext.system
      val serverBuilder: ServerBuilder = Http().newServerAt(host, port)
      val globalRoute: Route = GlobalRouteConcatenation.concatAll(this.createGlobalRoute(actorContext, actorSystem))
      serverBuilder.bind(globalRoute).onComplete {
        case success: Success[Http.ServerBinding] => actorSystem.log.info(s"HttpServer started at $host:$port")
        case failure: Failure[Http.ServerBinding] => actorSystem.log.info(s"HttpServer fail started at $host:$port, exception: ${failure.exception}")
      }
      Behaviors.empty
    }
  }
}
