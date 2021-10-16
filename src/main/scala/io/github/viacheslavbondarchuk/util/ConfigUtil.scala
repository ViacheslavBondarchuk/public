package io.github.viacheslavbondarchuk.util;

object ConfigUtil {
  val askTimeout: String = "my-app.routes.ask-timeout";

  //  def getAskTimeout(ActorSystem[_]): Duration = system.settings.config.getDuration("my-app.routes.ask-timeout")
}
