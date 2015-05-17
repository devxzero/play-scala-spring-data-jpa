name := "play-scala-spring-data-jpa"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
    javaJpa,
//    "org.springframework" % "spring-context" % "3.2.2.RELEASE",
    "javax.inject" % "javax.inject" % "1",
    "org.springframework.data" % "spring-data-jpa" % "1.8.0.RELEASE",
    "org.springframework" % "spring-core" % "4.1.4.RELEASE",
    "org.hibernate" % "hibernate-core" % "4.3.9.Final",
    "org.hibernate" % "hibernate-entitymanager" % "4.3.9.Final",
//    "org.mockito" % "mockito-core" % "1.9.5" % "test",
//    "mysql" % "mysql-connector-java" % "5.1.35"
    "com.h2database" % "h2" % "1.4.187"
)

lazy val root = (project in file(".")).enablePlugins(PlayScala)

// Possibly causes the error java.util.concurrent.TimeoutException: Futures timed out after [300000 milliseconds]. Disable if so.
fork in run := true