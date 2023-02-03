name := "country-codes"
version := "0.0.10"

val jacksonVersion = "2.13.5"

libraryDependencies ++= Seq(
  "org.scala-lang"                   % "scala-reflect"           % scalaVersion.value,
  "org.scalatest"                   %% "scalatest"               % "3.2.11" % Test,
  "com.fasterxml.jackson.core"       % "jackson-core"            % jacksonVersion,
  "com.fasterxml.jackson.core"       % "jackson-annotations"     % jacksonVersion,
  "com.fasterxml.jackson.core"       % "jackson-databind"        % jacksonVersion,
  "com.fasterxml.jackson.module"    %% "jackson-module-scala"    % jacksonVersion,
  "com.fasterxml.jackson.dataformat" % "jackson-dataformat-yaml" % jacksonVersion,
)
