package snakeandladder

import snakeandladder.engine.GameEngine
/* Launcher */
object Launcher {
  def main(args : Array[String]): Unit ={
    var app = new GameEngine()
    app.startMainThread
  }
}
