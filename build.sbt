/* Scala Template for my training */

name := "study_doobie"

version := "0.1.0"

scalaVersion := "2.11.8"

organization := "com.github.tanacasino"

scalacOptions ++= (
  "-feature" ::
  "-deprecation" ::
  "-unchecked" ::
  "-Xlint" ::
  "-Ywarn-dead-code" ::
  "-Ywarn-unused-import" ::
  "-language:existentials" ::
  "-language:higherKinds" ::
  "-language:implicitConversions" ::
  Nil
)

javacOptions in compile ++= Seq(
  "-encoding", "UTF-8",
  "-source", "1.8",
  "-target", "1.8"
)

lazy val doobieVersion = "0.3.0"

lazy val http4sVersion = "0.14.6a"

lazy val scalatestVersion = "3.0.0"

lazy val cirveVersion = "0.5.1"


libraryDependencies ++= {
  Seq(
    "org.tpolecat"       %%  "doobie-core"               % doobieVersion,

    "org.http4s"         %% "http4s-dsl"                 % http4sVersion,
    "org.http4s"         %% "http4s-blaze-server"        % http4sVersion,
    "org.http4s"         %% "http4s-blaze-client"        % http4sVersion,
    "org.http4s"         %% "http4s-circe"               % http4sVersion,

    "io.circe"           %% "circe-core"                 % cirveVersion,
    "io.circe"           %% "circe-generic"              % cirveVersion,
    "io.circe"           %% "circe-parser"               % cirveVersion,

    "mysql"               % "mysql-connector-java"       % "5.1.39",
     "ch.qos.logback"     % "logback-classic"            % "1.1.7",


    "org.scalatest"      %%  "scalatest"                 % scalatestVersion   % "test"
  )
}


// Customize sbt prompt
import com.scalapenos.sbt.prompt._
import SbtPrompt.autoImport._

promptTheme := PromptTheme(
  List(
    text("[", fg(white)),
    currentProject(fg(cyan)),
    text("] ", fg(white)),
    gitBranch(clean = fg(green), dirty = fg(red)),
    text(" $ ", fg(yellow))
  )
)

shellPrompt := (implicit state => promptTheme.value.render(state))


// Format sources by using scalariform
import com.typesafe.sbt.SbtScalariform.autoImport._
import scalariform.formatter.preferences._

scalariformPreferences := scalariformPreferences.value
  .setPreference(DoubleIndentClassDeclaration, false)

scalariformSettings

