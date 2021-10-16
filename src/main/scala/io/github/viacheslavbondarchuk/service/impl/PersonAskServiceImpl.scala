package io.github.viacheslavbondarchuk.service.impl

import akka.actor.typed.scaladsl.AskPattern.{Askable, schedulerFromActorSystem}
import akka.actor.typed.{ActorRef, ActorSystem}
import akka.util.Timeout
import io.github.viacheslavbondarchuk.command.impl.PersonCommand.{PersonCreate, PersonDelete, PersonGet}
import io.github.viacheslavbondarchuk.command.{ActionPerformed, Command}
import io.github.viacheslavbondarchuk.model.Person
import io.github.viacheslavbondarchuk.service.AskService

import java.time.Duration
import scala.concurrent.Future

class PersonAskServiceImpl(val personRegistry: ActorRef[Command], implicit val actorSystem: ActorSystem[_]) extends AskService[Person, Long] {
  private implicit val timeout: Timeout = Timeout.create(Duration.ofSeconds(5))

  override def get(id: Long): Future[Person] = personRegistry.ask(PersonGet(id, _: ActorRef[Person]))

  override def create(entity: Person): Future[ActionPerformed] = personRegistry.ask(PersonCreate(entity, _: ActorRef[ActionPerformed]))

  override def delete(id: Long): Future[ActionPerformed] = personRegistry.ask(PersonDelete(id, _: ActorRef[ActionPerformed]))
}
