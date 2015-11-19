organization  := "info.lotharschulz"

name          := "rest01"

version       := "0.1"

scalaVersion  := "2.11.6"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV
    ,"io.spray"           %%  "spray-routing" % sprayV
    ,"io.spray"           %%  "spray-httpx"   % sprayV
    ,"io.spray"           %%  "spray-testkit" % sprayV  % "test"
    ,"io.spray"           %%  "spray-json"    % "1.3.2"

    ,"com.typesafe.akka"  %%  "akka-actor"    % akkaV
    ,"com.typesafe.akka"  %%  "akka-slf4j"    % akkaV
    ,"com.typesafe.akka"  %%  "akka-testkit"  % akkaV   % "test"

    ,"org.specs2"         %%  "specs2-core"   % "2.3.11" % "test"

    ,"ch.qos.logback"     %   "logback-classic" % "1.1.3"
  )
}

// intelliJ
// http://stackoverflow.com/questions/19578611/idea-complains-about-revolver-settings-when-trying-to-parse-spray-templates-bui
Revolver.settings: Seq[sbt.Def.Setting[_]]