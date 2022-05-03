ThisBuild / organization := "io.wunderschild"
ThisBuild / scalaVersion := "2.12.12"
ThisBuild / crossScalaVersions := Seq("2.11.12", "2.12.12")

lazy val root = (project in file("."))
  .settings(commonSettings)
  .aggregate(project in file("country-codes"))

lazy val commonSettings = Seq(
  // Publish to GitHub Packages
  githubOwner := "wunderschild",
  githubRepository := "country-codes",
  githubTokenSource := TokenSource.GitConfig("github.token")
)
