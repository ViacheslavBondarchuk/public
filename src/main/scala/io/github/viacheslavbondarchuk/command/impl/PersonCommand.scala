package io.github.viacheslavbondarchuk.command.impl

import akka.actor.typed.ActorRef
import io.github.viacheslavbondarchuk.command.{ActionPerformed, Create, Delete, Get}
import io.github.viacheslavbondarchuk.model.Person

object PersonCommand {
  final case class PersonGet(id: Long, replyTo: ActorRef[Person]) extends Get[Person, Long](id, replyTo)
  final case class PersonDelete(id: Long, replyTo: ActorRef[ActionPerformed]) extends Delete[Long](id, replyTo)
  final case class PersonCreate(person: Person, replyTo: ActorRef[ActionPerformed]) extends Create[Person](person, replyTo)
}
