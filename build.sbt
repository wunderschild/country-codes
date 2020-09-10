lazy val commonSettings = (project in file(".")).
  settings(
    organization := "io.wunderschild",
    name := "country-codes",
    version := "0.0.2",
    scalaVersion := "2.11.12",
    githubOwner := "wunderschild",
    githubRepository := "country-codes")

val jacksonVersion = "2.6.7"
val jacksonModuleVersion  = "2.6.7.1"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonModuleVersion,
  "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % jacksonVersion
)