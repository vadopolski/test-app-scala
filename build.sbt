ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.17"

lazy val root = (project in file("."))
  .settings(
    name := "test-app-scala"
  )


val circeVersion = "0.14.1"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
).map(_ % circeVersion)


// https://mvnrepository.com/artifact/com.github.plokhotnyuk.jsoniter-scala/jsoniter-scala-core
libraryDependencies += "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % "2.13.5"
libraryDependencies += "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "2.13.5" % "provided"


// https://mvnrepository.com/artifact/io.scalaland/chimney
libraryDependencies += "io.scalaland" %% "chimney" % "0.6.2"


libraryDependencies += "com.github.scopt" %% "scopt" % "4.1.0"

libraryDependencies += "com.github.pureconfig" %% "pureconfig" % "0.17.2"


//dependencyOverrides += "log4j" % "log4j" % "1.2.17"