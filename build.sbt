name := "play-scala-spring-data-jpa"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
    jdbc,
    cache,
    ws,
    specs2 % Test,
    "javax.inject" % "javax.inject" % "1",
    "org.springframework" % "spring-context" % "4.1.7.RELEASE",
    "org.springframework.data" % "spring-data-jpa" % "1.8.1.RELEASE",
    "org.hibernate" % "hibernate-core" % "4.3.10.Final",
    "org.hibernate" % "hibernate-entitymanager" % "4.3.10.Final",
    "com.h2database" % "h2" % "1.4.187",
    "org.slf4j" % "slf4j-api" % "1.7.7",
    "org.slf4j" % "jcl-over-slf4j" % "1.7.7",
    "org.slf4j" % "log4j-over-slf4j" % "1.7.7"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
