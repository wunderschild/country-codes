lazy val commonSettings = (project in file(".")).
  settings(
    name := "country-codes",
    version := "0.0.1",
    scalaVersion := "2.11.12",
    githubOwner := "wunderschild",
    githubRepository := "country-codes")

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "com.fasterxml.jackson.core" % "jackson-core" % "2.10.3",
  "com.fasterxml.jackson.core" % "jackson-annotations" % "2.10.3",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.10.3",
  "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % "2.10.3",
  "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % "2.10.3"
)