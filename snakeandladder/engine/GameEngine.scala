package snakeandladder.engine

import java.awt.event.{MouseListener, KeyListener}
import java.awt.image.BufferStrategy
import java.awt.{Color, Graphics,Graphics2D, Toolkit,RenderingHints}
import snakeandladder.gamecomponent.Button
import snakeandladder.gamestate.{GameState, GameStateManager}

import snakeandladder.gameobject.{GameObject, Dice, Player, Board}

class GameEngine extends Runnable{
  private var mainThread : Thread = null
  private var gameDisplay : GameDisplay = null
  private var runningStatus : Boolean = false
  private var bufferStrategy : BufferStrategy = null
  private var graphics : Graphics = null
  private var board : Board = null
  private def isRunning : Boolean = runningStatus


  private def initEngine : Unit = {
    /* inisiasi gameDisplay */
    gameDisplay = new GameDisplay
    gameDisplay.getDisplayCanvas.addMouseListener(GameStateManager)
    gameDisplay.getDisplayCanvas.addKeyListener(GameStateManager)
    gameDisplay.getDisplayCanvas.addMouseMotionListener(GameStateManager)
  }

  /**
   * Ada banyak komponen yang akan di inisialisasi disini
   */
  private def initComponents : Unit = {
    val playState : GameState = new GameState
    board = new Board(0,0,10,10)
    board.populateSnake(5)
    board.setDice(new Dice(700,0))
    board.addPlayer(new Player(board.getTileByNumber(1),board))

    playState.addComponentObject(board)
    playState.addComponentObject(board.getDice)
    playState.addComponentObjects(board.getPlayers.asInstanceOf[Array[GameObject]])
    playState.addComponentObjects(board.getSnakes.asInstanceOf[Array[GameObject]])
    playState.addComponentObject(new Button(650,100,"Click Me",100))
    GameStateManager.addState(playState,"PlayState")
    GameStateManager.setActiveState("PlayState")
  }

  /**
   * Main render
   */
  private def runGameState : Unit = {
    bufferStrategy = gameDisplay.getDisplayCanvas.getBufferStrategy
    if(bufferStrategy == null){
      /* Triple buffering */
      gameDisplay.getDisplayCanvas.createBufferStrategy(3)
      return
    }
    graphics = bufferStrategy.getDrawGraphics

    /* Apakah menggunakan antialias */
    if(GameEngineSettings.getAntialiasSettings){
      antialiasing(graphics)
    }
    /* Clearing screen */
    graphics.clearRect(0,0,GameDisplaySettings.WINDOW_WIDTH,GameDisplaySettings.WINDOW_HEIGHT)
    /* rendering */
    GameStateManager.runState(graphics)
    Toolkit.getDefaultToolkit.sync
    bufferStrategy.show
    graphics.dispose
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
    initEngine
    initComponents
    var delta : Double = 0
    var currentTime : Long = 0
    var lastTime : Long = System.nanoTime()

    /* Game loop */
    while(isRunning){
       currentTime = System.nanoTime()
       delta += (currentTime - lastTime).asInstanceOf[Double] / GameEngineSettings.getDefaultUpdatePeriod
       lastTime = currentTime
       if(delta >= 1){
         runGameState
         delta -= 1
       }
    }
  }

}

