package io.github.viacheslavbondarchuk.command

import akka.actor.typed.ActorRef

sealed trait Command
final case class ActionPerformed(msg: String)
abstract class Get[T, ID](id: ID, replyTo: ActorRef[T]) extends Command
abstract class Delete[ID](id: ID, replyTo: ActorRef[ActionPerformed]) extends Command
abstract class Create[T](entity: T, replyTo: ActorRef[ActionPerformed]) extends Command

