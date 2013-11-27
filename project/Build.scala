import sbt._
import sbt.Keys._

object AkkaSamples extends Build {
  lazy val sere = Project(
    id = "akka-samples",
    base = file("."),
    settings = defaultSettings,
    aggregate = Seq(fetcherSample, mapReduceSample)
  )

  lazy val fetcherSample = Project(
    id = "fetcher",
    base = file("fetcher"),
    settings = defaultSettings ++ Seq(
      libraryDependencies ++= Dependencies.fetcher
    )
  )

  lazy val mapReduceSample = Project(
    id = "map-reduce",
    base = file("map-reduce"),
    settings = defaultSettings ++ Seq(
      libraryDependencies ++= Dependencies.mapreduce
    )
  )

  lazy val buildSettings = Defaults.defaultSettings ++ Seq(
    version := "0.1-SNAPSHOT",
    crossPaths := false,
    scalaVersion := "2.10.2",
    organization := "akka.samples",
    parallelExecution in This := false
  )

  lazy val defaultSettings = buildSettings ++ Seq(
    resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/",
    resolvers += "Typesafe Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",

    resolvers += "Twitter Repo" at "http://maven.twttr.com",
    resolvers += "Conjars Repo" at "http://conjars.org/repo",
    resolvers += "Clojars Repo" at "https://clojars.org/repo",

    scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked"),
    javacOptions  ++= Seq("-Xlint:deprecation", "-source", "1.7", "-Xlint:unchecked")
  )
}

object Dependencies {
  import Dependency._
  val mapreduce = Seq(
    akkaActor, akkaKernel, akkaContrib, akkaSlf4j, scalaTest
  )
  val fetcher = Seq(
    crawler4j, akkaActor, akkaKernel, akkaContrib, akkaSlf4j, scalaTest
  )
}

object Dependency {
  object V {
    val akka = "2.2.3"
    val scalatest = "1.9.1"
  }

  val akkaSlf4j   = "com.typesafe.akka" %% "akka-slf4j" % V.akka
  val akkaActor   = "com.typesafe.akka" %% "akka-actor" % V.akka
  val akkaKernel  = "com.typesafe.akka" %% "akka-kernel" % V.akka
  val akkaContrib = "com.typesafe.akka" %% "akka-contrib" % V.akka
  val akkaCluster = "com.typesafe.akka" %% "akka-cluster" % V.akka

  val scalaj      = "org.scalaj" %% "scalaj-time" % "0.7"
  val scalaTest   = "org.scalatest" %% "scalatest" % V.scalatest % "test"

  val crawler4j   = "edu.uci.ics" % "crawler4j" % "3.5"
}
