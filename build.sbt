name := """InternManagement"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  cache,
  ws,
  "com.typesafe.play"   %%    "play-slick"              %   "1.1.1",
  "com.typesafe.play"   %%    "play-slick-evolutions"   %   "1.1.1",
  "com.h2database"      %     "h2"                    %   "1.4.187" ,
   specs2 % Test,
  "org.webjars" %% "webjars-play" % "2.4.0-1",
  "org.webjars" % "bootstrap" % "3.1.1-2",
  "com.github.nscala-time" %% "nscala-time" % "2.10.0",
  "com.adrianhurt" %% "play-bootstrap" % "1.0-P24-B3-SNAPSHOT",
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc4"
)


routesGenerator := InjectedRoutesGenerator
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"

//coverageExcludedPackages :="<empty>;router\\..*;"
resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

javaOptions in Test += "-Dconfig.file=conf/test.conf"

parallelExecution in Test := false