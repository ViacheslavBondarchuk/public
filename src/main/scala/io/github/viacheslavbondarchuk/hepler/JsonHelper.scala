package io.github.viacheslavbondarchuk.hepler

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import io.github.viacheslavbondarchuk.command.ActionPerformed
import io.github.viacheslavbondarchuk.model.Person
import spray.json.{DefaultJsonProtocol, NullOptions, RootJsonFormat}

trait JsonHelper extends SprayJsonSupport with DefaultJsonProtocol with NullOptions {
  implicit val actionPerformedJsonFormat: RootJsonFormat[ActionPerformed] = jsonFormat1(ActionPerformed)
  implicit val personJsonFormat: RootJsonFormat[Person] = jsonFormat2(Person)
}
