package snake_and_ladder

import snake_and_ladder.engine.GameEngine
/* Launcher */
object Launcher {
  def main(args : Array[String]): Unit ={
    var app = new GameEngine()
    app.startMainThread
  }
}
