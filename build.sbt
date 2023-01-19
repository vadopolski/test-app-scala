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


//      local machine

// hadoop yarn cluster
// master + worker


// image => docker repo 2.4 + 3.0 + 3.3
// kubernetes cluster



// https://mvnrepository.com/artifact/io.scalaland/chimney
libraryDependencies += "io.scalaland" %% "chimney" % "0.6.2"

