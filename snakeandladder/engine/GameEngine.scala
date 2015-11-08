package snakeandladder.engine

import java.awt.image.BufferStrategy
import java.awt.{Color, Graphics,Graphics2D, Toolkit,RenderingHints}

import snakeandladder.gameobject.{Player, Board}

class GameEngine extends Runnable{
  private var mainThread : Thread = null
  private var gameDisplay : GameDisplay = null
  private var runningStatus : Boolean = false
  private var bufferStrategy : BufferStrategy = null
  private var graphics : Graphics = null

  private var board : Board = null

  /**
   * Ada banyak komponen yang akan di inisialisasi disini
   */
  private def initComponents : Unit = {
    /* inisiasi gameDisplay */
    gameDisplay = new GameDisplay
    /* Bagian ini akan dipindahkan ke GameState*****************/
      /* inisiasi game board */
      board = new Board(0,0,10,10)
      board.initTiles
      /* Percobaan inisiasi player */
      board.player = new Player(board.getTileByNumber(1),board)
    /***********************************************************/
  }

  /**
   * Main render
   */
  private def renderComponents : Unit = {
    bufferStrategy = gameDisplay.getDisplayCanvas.getBufferStrategy
    if(bufferStrategy == null){
      /* Triple buffering */
      gameDisplay.getDisplayCanvas.createBufferStrategy(3)
      return
    }
    graphics = bufferStrategy.getDrawGraphics

    /* Apakah menggunakan antialias */
    if(GameEngine.USE_ANTIALIAS){
      antialiasing(graphics)
    }
    /* Clearing screen */
    graphics.clearRect(0,0,GameEngine.WINDOW_WIDTH,GameEngine.WINDOW_HEIGHT)
    /* rendering */
    mainRender
    Toolkit.getDefaultToolkit.sync
    bufferStrategy.show
    graphics.dispose
  }
  private def mainRender : Unit = {
    /* Semua komponen yang harus dirender masuk disini */

    /* Bagian ini akan dipindahkan ke GameState*****************/
    /* render board */
    board.render(graphics)
    /***********************************************************/

  }

  private def updateComponents : Unit = {
    /* Semua komponen yang mempunyai event masuk disini */

    /* Bagian ini akan dipindahkan ke GameState*****************/
    board.update()
    /***********************************************************/

  }

  private def antialiasing(graphics : Graphics) : Unit = {
    /**
     * Mengkonfigurasi graphics untuk mengaktifkan antialiasing
     */
    var g2d : Graphics2D = graphics.asInstanceOf[Graphics2D]
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
  }


  def isRunning : Boolean = runningStatus

  /**
   * Run game thread
   */
  def startMainThread : Unit = {
    synchronized{
      if(isRunning) return
      mainThread = new Thread(this)
      runningStatus = true
      mainThread.start
    }
  }

  /**
   * Stop game thread
   */
  def stopMainThread : Unit = {
    synchronized{
      if(!isRunning) return
      runningStatus = false
      mainThread.join
    }
  }

  /**
   * Called when thread start,
   */
  override def run : Unit = {
    initComponents
    var delta : Double = 0
    var currentTime : Long = 0
    var lastTime : Long = System.nanoTime()

    /* Game loop */
    while(isRunning){
       currentTime = System.nanoTime()
       delta += (currentTime - lastTime).asInstanceOf[Double] / GameEngine.DEFAULT_UPDATE_PERIOD
       lastTime = currentTime
       if(delta >= 1){
         renderComponents
         updateComponents
         delta -= 1
       }
    }
  }

}
/* Statics */
object GameEngine {
  val TITLE : String = "Snake and Ladder"
  val WINDOW_WIDTH : Int = 800
  val WINDOW_HEIGHT : Int = 800
  val DEFAULT_FPS : Int = 60
  val DEFAULT_UPDATE_PERIOD : Double = 1000000000 / GameEngine.DEFAULT_FPS
  var USE_ANTIALIAS : Boolean = true
}

