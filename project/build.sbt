resolvers += Classpaths.typesafeResolver

resolvers ++= Seq(
  "oss sonatype" at "https://oss.sonatype.org/content/repositories/snapshots"
)

//addSbtPlugin("com.github.scct" %% "sbt-scct" % "0.2-SNAPSHOT")

//addSbtPlugin("com.github.theon" %% "xsbt-coveralls-plugin" % "0.0.4-SNAPSHOT")