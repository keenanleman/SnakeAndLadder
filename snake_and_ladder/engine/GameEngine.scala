package snake_and_ladder.engine

import java.awt.image.BufferStrategy
import java.awt.{Color, Graphics, Toolkit}

class GameEngine extends Runnable{
  private var mainThread : Thread = null
  private var gameDisplay : GameDisplay = null
  private var runningStatus : Boolean = false
  private var bufferStrategy : BufferStrategy = null
  private var graphics : Graphics = null

  /**
   * Ada banyak komponen yang akan di inisialisasi disini
   */
  private def initComponents : Unit = {
    gameDisplay = new GameDisplay
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
    /* Clearing screeen */
    graphics.clearRect(0,0,GameEngine.WINDOW_WIDTH,GameEngine.WINDOW_HEIGHT)
    /* rendering */
    render
    Toolkit.getDefaultToolkit.sync
    bufferStrategy.show
    graphics.dispose
  }
  private def render : Unit = {
    /* Semua komponen yang harus dirender masuk disisi */
  }

  private def updateComponents : Unit = {
    /* Semua komponen yang mempunyai event masuk disini */
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
         render
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
}

