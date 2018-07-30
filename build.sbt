name := "http-bin"

version := "1.0"

scalaVersion in ThisBuild := "2.12.2"

val Http4sVersion = "0.18.0-M4"
val AwsSdkVersion = "2.0.0-preview-1"

lazy val httpBin =
  (project in file("httpbin"))
    .settings(
        test in assembly := {},
        assemblyMergeStrategy in assembly := {
          case PathList("META-INF", xs @ _*) => MergeStrategy.discard
          case x => MergeStrategy.first
        },
        mainClass in Compile := Some("httpbin.Server"),
        libraryDependencies ++= Seq(
          "software.amazon.awssdk" % "elasticache" % AwsSdkVersion,
          "net.debasishg" %% "redisclient" % "3.4",
          "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
          "org.http4s" %% "http4s-dsl" % Http4sVersion,
          "ch.qos.logback" % "logback-classic" % "1.2.1",
          "org.scalatest" %% "scalatest" % "3.0.1" % "test"
        )
    )



lazy val root =
  (project in file(".")).aggregate(httpBin)


onLoad in Global ~= (_ andThen ("project httpBin" :: _))

