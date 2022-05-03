lazy val commonSettings = (project in file(".")).
  settings(
    organization := "io.wunderschild",
    name := "country-codes",
    version := "0.0.4",
    scalaVersion := "2.12.12",
    crossScalaVersions := Seq("2.11.12", "2.12.12"),
    githubOwner := "wunderschild",
    githubRepository := "country-codes",
    githubTokenSource := TokenSource.GitConfig("github.token")
  )

val jacksonVersion = "2.10.5"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % scalaVersion.value,
  "org.scalatest" %% "scalatest" % "3.2.9" % Test,
  "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion,
  "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion,
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % jacksonVersion,
  "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % jacksonVersion
)
