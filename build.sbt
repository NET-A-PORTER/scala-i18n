name := "scala-i18n"

organization  := "com.netaporter"

version       := "0.1"

scalaVersion  := "2.10.2"

crossScalaVersions := Seq("2.9.3", "2.10.2")

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

scalacOptions := Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "1.9.1" % "test")

//seq(sbt.scct.ScctPlugin.instrumentSettings : _*)

//seq(com.github.theon.coveralls.CoverallsPlugin.coverallsSettings: _*)

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra := (
  <url>https://github.com/Net-A-Porter/scala-i18n</url>
  <licenses>
    <license>
      <name>Apache 2</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:Net-A-Porter/scala-i18n.git</url>
    <connection>scm:git@github.com:Net-A-Porter/scala-i18n.git</connection>
  </scm>
  <developers>
    <developer>
      <id>theon</id>
      <name>Ian Forsey</name>
      <url>http://theon.github.io</url>
    </developer>
  </developers>)