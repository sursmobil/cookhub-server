enablePlugins(JavaAppPackaging)

name := "cookhub-server"
version := "0.1"
scalaVersion := "2.12.4"

val akkaVersion = "2.4.19"
val akkaHttpVersion = "10.0.11"
val elasticVersion = "5.6.0"
val elastic4sVersion = "5.6.0"
val scalaTestVersion = "3.0.4"
val codehaleMetricsVersion = "3.0.2"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2",
  "com.unstablebuild" %% "settler" % "1.0.0",
  "org.mongodb.scala" %% "mongo-scala-driver" % "2.1.0",
  "org.scalamock" %% "scalamock" % "4.0.0" % Test,
  "org.scalatest" %% "scalatest" % "3.0.4" % Test,
  "io.spray" %% "spray-json" % "1.3.4",

  "com.typesafe.akka" %% "akka-http-core" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
)


