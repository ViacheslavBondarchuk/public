lazy val scalapbVersion = "0.11.1"

addSbtPlugin("io.spray" % "sbt-revolver" % "0.9.1")
addSbtPlugin("com.thesamet" % "sbt-protoc" % "1.0.3")

libraryDependencies ++=Seq(
  "com.thesamet.scalapb" %% "compilerplugin" % scalapbVersion
)
