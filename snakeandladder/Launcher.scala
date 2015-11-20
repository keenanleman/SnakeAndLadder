package snakeandladder

import snakeandladder.engine.GameEngine
/**
 * Kelas statik yang digunakan untuk me-launch game
 */
object Launcher {
  /**
   * Kelas main dimana GameEngine di instansiasi dan dijalankan
   */
  def main(args : Array[String]): Unit ={
    var app = new GameEngine()
    app.startMainThread
  }
}
