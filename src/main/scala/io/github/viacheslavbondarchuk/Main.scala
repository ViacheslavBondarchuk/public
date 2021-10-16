package io.github.viacheslavbondarchuk

object Main {
  def main(args: Array[String]): Unit = {
    HttpServerStarter.start("localhost", 8080, "PublicHttpServer")
  }
}