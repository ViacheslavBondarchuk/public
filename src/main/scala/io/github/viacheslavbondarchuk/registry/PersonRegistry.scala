package io.github.viacheslavbondarchuk.registry

import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import io.github.viacheslavbondarchuk.command.{ActionPerformed, Command}
import io.github.viacheslavbondarchuk.command.impl.PersonCommand.{PersonCreate, PersonDelete, PersonGet}
import io.github.viacheslavbondarchuk.model.Person

object PersonRegistry {
  def apply(): Behavior[Command] = Behaviors.receiveMessage {
    case PersonGet(id, replyTo) => {
      replyTo ! Person(id, "test")
      return Behaviors.same
    }
    case PersonDelete(id, replyTo) => {
      replyTo ! ActionPerformed(s"user with id: $id has been deleted")
      return Behaviors.same
    }
    case PersonCreate(person, replyTo) => {
      replyTo ! ActionPerformed(s"user has been created")
      return Behaviors.same
    }
  }
}
