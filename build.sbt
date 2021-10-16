lazy val akkaHttpVersion = "10.2.6"
lazy val akkaVersion = "2.6.17"
lazy val logbackVersion = "1.2.6"
lazy val scalaTestVersion = "3.2.9"

lazy val root = (project in file(".")).
  settings(
    name := "public",

    Compile / PB.targets := Seq(
      scalapb.gen() -> (Compile / sourceManaged).value / "scalapb"
    ),

    inThisBuild(List(
      organization := "io.github.viachelsabondarchuk",
      scalaVersion := "2.13.4"
    )),

    resolvers ++= Seq(
      Resolver.mavenLocal,
      Resolver.mavenCentral
    ),

    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      "ch.qos.logback" % "logback-classic" % logbackVersion,
      "com.thesamet.scalapb" %% "scalapb-runtime" % scalapb.compiler.Version.scalapbVersion % "protobuf",

      "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
      "org.scalatest" %% "scalatest" % scalaTestVersion % Test
    )
  )