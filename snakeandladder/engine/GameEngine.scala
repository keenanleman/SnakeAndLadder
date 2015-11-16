package snakeandladder.engine

import java.awt.image.BufferStrategy
import java.awt.{Color,Graphics,Graphics2D,RenderingHints,Toolkit,Font}
import java.io.File
import javax.imageio.ImageIO
import snakeandladder.gamestate.{PrePlayState, GameState, GameStateManager}
import snakeandladder.utility.AssetManager

class GameEngine extends Runnable{
  private var mainThread : Thread = null
  private var gameDisplay : GameDisplay = null
  private var runningStatus : Boolean = false
  private var bufferStrategy : BufferStrategy = null
  private var graphics : Graphics = null

  private def isRunning : Boolean = runningStatus


  private def initEngine : Unit = {
    /* inisiasi gameDisplay */
    gameDisplay = new GameDisplay
    gameDisplay.getDisplayCanvas.addMouseListener(GameStateManager)
    gameDisplay.getDisplayCanvas.addKeyListener(GameStateManager)
    gameDisplay.getDisplayCanvas.addMouseMotionListener(GameStateManager)

    bufferStrategy = gameDisplay.getDisplayCanvas.getBufferStrategy
    if(bufferStrategy == null){
      /* Triple buffering */
      gameDisplay.getDisplayCanvas.createBufferStrategy(3)
      bufferStrategy = gameDisplay.getDisplayCanvas.getBufferStrategy
    }
    graphics = bufferStrategy.getDrawGraphics
  }

  /**
   * Ada banyak komponen yang akan di inisialisasi disini
   */
  private def initComponents : Unit = {
    var backgroundBlurPrimary = Color.WHITE
    AssetManager.registerAsset("BackgroundWood",ImageIO.read(new File(ResourcePath.BACKGROUND_KAYU_PATH)))
    AssetManager.registerAsset("BackgroundBlur",new Color(
      backgroundBlurPrimary.getRed,
      backgroundBlurPrimary.getGreen,
      backgroundBlurPrimary.getBlue,
      120
    ))

    var defaultFont : Font = Font.createFont(Font.TRUETYPE_FONT,new File(ResourcePath.DEFAULT_FONT_PATH))
    AssetManager.registerAsset("DefaultFont",defaultFont.deriveFont(Font.PLAIN,15),
      graphics.getFontMetrics(defaultFont.deriveFont(Font.PLAIN,15)))

    AssetManager.registerAsset("PrimaryShadowColor",new Color(Color.black.getRed,Color.black.getGreen,Color.black.getBlue,50))
    AssetManager.registerAsset("SecondaryShadowColor",new Color(Color.blue.getRed,Color.blue.getGreen,Color.blue.getBlue,100))

    AssetManager.registerAsset("ButtonTitleColor", Color.BLACK)
    AssetManager.registerAsset("ButtonFaceColor", Color.WHITE)

    var LabelPrimaryColor = Color.WHITE
    AssetManager.registerAsset("LabelColor", new Color(
      LabelPrimaryColor.getRed,
      LabelPrimaryColor.getGreen,
      LabelPrimaryColor.getBlue,
      200
    ))
    AssetManager.registerAsset("LabelTitleColor", Color.BLACK)

    AssetManager.registerAsset("CheckboxTitleColor", Color.BLACK)
    AssetManager.registerAsset("CheckboxCheckedColor", Color.BLUE)
    AssetManager.registerAsset("CheckboxUnCheckedColor", Color.WHITE)

    AssetManager.registerAsset("PanelColor",new Color(184,115,51,180))

    var DicePrimaryColor = Color.YELLOW
    AssetManager.registerAsset("DiceColor",new Color(
      DicePrimaryColor.getRed,
      DicePrimaryColor.getGreen,
      DicePrimaryColor.getBlue,
      200
    ))

    val prePlayState : GameState = new PrePlayState("PrePlayState")
    GameStateManager.addState(prePlayState)

    GameStateManager.setActiveState("PrePlayState")
  }

  /**
   * Main render
   */
  private def runGameState : Unit = {
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

