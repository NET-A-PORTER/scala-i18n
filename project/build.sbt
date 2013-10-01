resolvers += Classpaths.typesafeResolver

resolvers ++= Seq(
  //"oss sonatype" at "https://oss.sonatype.org/content/repositories/snapshots",
  Resolver.url("bintray-sbt-plugin-releases", url("http://dl.bintray.com/content/sbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)
)

addSbtPlugin("me.lessis" % "bintray-sbt" % "0.1.1")

//addSbtPlugin("com.github.scct" %% "sbt-scct" % "0.2-SNAPSHOT")

//addSbtPlugin("com.github.theon" %% "xsbt-coveralls-plugin" % "0.0.4-SNAPSHOT")