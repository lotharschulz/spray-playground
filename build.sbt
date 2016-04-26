organization  := "info.lotharschulz"
name          := "spray-playground"
version       := "0.1"
scalaVersion  := "2.11.7"

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-Xlint", "-Ywarn-adapted-args", "-Xfatal-warnings", "-feature")
javacOptions ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.3"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV
    ,"io.spray"           %%  "spray-routing" % sprayV
    ,"io.spray"           %%  "spray-httpx"   % sprayV
    ,"io.spray"           %%  "spray-json"    % "1.3.2"
    ,"io.spray"           %%  "spray-testkit" % sprayV  % "test"

    ,"com.typesafe.akka"  %%  "akka-actor"    % akkaV
    ,"com.typesafe.akka"  %%  "akka-slf4j"    % akkaV
    ,"com.typesafe.akka"  %%  "akka-testkit"  % akkaV    % "test"

    ,"org.specs2"         %%  "specs2-core"   % "2.3.11" % "test"
    ,"org.scalatest"      %%  "scalatest"     % "2.2.2"  % "test"

    ,"ch.qos.logback"     %   "logback-classic" % "1.1.3"
  )
}

// intelliJ
// http://stackoverflow.com/questions/19578611/idea-complains-about-revolver-settings-when-trying-to-parse-spray-templates-bui
Revolver.settings: Seq[sbt.Def.Setting[_]]

pomExtra :=
  <scm>
    <url>https://github.com/lotharschulz/spray-playground</url>
    <connection>scm:git:git@github.com:lotharschulz/spray-playground.git</connection>
    <developerConnection>scm:git:https://github.com/lotharschulz/spray-playground.git</developerConnection>
  </scm>
    <developers>
      <developer>
        <id>lotharschulz</id>
        <name>lothar</name>
        <email>mail[at]lothar[minus]schulz[dot]info</email>
        <url>https://github.com/lotharschulz</url>
      </developer>
    </developers>