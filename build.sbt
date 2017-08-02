import java.io.OutputStream

name := "TheElementsOfComputingSystems"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.1"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1" % "test"

fork in run := true
outputStrategy := Some(StdoutOutput)
connectInput in run := true