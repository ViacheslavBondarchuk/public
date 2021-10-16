package io.github.viacheslavbondarchuk.service

import io.github.viacheslavbondarchuk.command.ActionPerformed

import scala.concurrent.Future

trait AskService[T, ID] {
  def get(id: ID): Future[T]
  def create(entity: T): Future[ActionPerformed]
  def delete(id: ID): Future[ActionPerformed]
}
