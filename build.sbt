name := "scalearn"
version := "0.0.1-SNAPSHOT"

scalaVersion := "2.13.0"

scalacOptions ++= Seq(
  "-Ypartial-unification",
  "-Yrangepos",
  "-Ywarn-unused-import",
  "-Ywarn-adapted-args",
  "-Ywarn-unused",
  "-unchecked",
  "-feature",
  "-deprecation",
  "-language:existentials",
  "-language:higherKinds",
  "-encoding",
  "utf8"
)

libraryDependencies += "org.typelevel" %% "cats-core" % "2.1.0"



addCompilerPlugin("com.olegpy"    %% "better-monadic-for" % "0.3.1")
addCompilerPlugin("org.typelevel" %% "kind-projector"     % "0.10.3")
