lazy val root = (project in file(".")).
settings(
  name := "SnakeAndLadder",
  scalaSource in Compile := baseDirectory.value / "snakeandladder",
  scalacOptions in (Compile,doc) := Seq("-groups", "-implicits"),
  mainClass in (Compile, packageBin) := Some("Launcher"),
  resourceDirectory in Compile := baseDirectory.value / "snakeandladder/resources"
  )
