package io.github.viacheslavbondarchuk.route

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives.{FutureDirectives, RouteDirectives}
import akka.http.scaladsl.server.{Directives, Route}
import io.github.viacheslavbondarchuk.hepler.JsonHelper
import io.github.viacheslavbondarchuk.model.Person
import io.github.viacheslavbondarchuk.service.impl.PersonAskServiceImpl

object PersonRoute extends JsonHelper {
  def createRoute(personAskServiceImpl: PersonAskServiceImpl): Route = Directives.pathPrefix("person") {
    Directives.concat(
      Directives.pathEnd {
        Directives.concat(
          Directives.get {
            FutureDirectives.onSuccess(personAskServiceImpl.get(1)) {
              person: Person => RouteDirectives.complete(StatusCodes.OK, person)
            }
          })
      })
  }

}
