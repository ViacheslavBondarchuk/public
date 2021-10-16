package io.github.viacheslavbondarchuk.route

import akka.http.scaladsl.server.{Route, RouteConcatenation}

import scala.language.implicitConversions

object GlobalRouteConcatenation extends RouteConcatenation {
  def concatAll(routes: Seq[Route]): Route = routes.reduce((acc: Route, next: Route) => acc ~ next)
}
