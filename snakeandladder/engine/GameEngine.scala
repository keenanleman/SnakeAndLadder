package snakeandladder.engine

import java.awt.image.BufferStrategy
import java.awt.{Color,Graphics,Graphics2D,RenderingHints,Toolkit,Font}
import java.io.File
import javax.imageio.ImageIO
import snakeandladder.gamestate._
import snakeandladder.utility.AssetManager

/**
 * Sebagai kelas utama yang mengatur bagaimana game akan dijalankan,
 * mengatur 'game loop', serta menginstansiasi objek-objek utama dan 
 * meregister resource yang digunakan game
 */
class GameEngine extends Runnable{
  /**
   * Thread yang digunakan sebagai thread utama dalam game
   */
  private var mainThread : Thread = null
  /**
   * Jendela dan canvas yang digunakan game engine untuk merender objek-objek
   * yang digunakan dalam game
   */
  private var gameDisplay : GameDisplay = null

  /**
   * Status apakah game Main Thread sedang berjalan
   */
  private var runningStatus : Boolean = false

  /**
   * Objek yang digunakan untuk merender pada canvas
   */
  private var bufferStrategy : BufferStrategy = null

  /**
   * Objek yang digunakan untuk merender pada canvas
   */
  private var graphics : Graphics = null

  /**
   * Method getter apakah game Main Thread sedang berjalan
   */
  private def isRunning : Boolean = runningStatus

  /**
   * Menginisiasi GameEngine
   */
  private def initEngine : Unit = {
    /* inisiasi gameDisplay */
    gameDisplay = new GameDisplay

    /* Menambahkan evetn listener ke GameDisplay agar GameStateManager
     * dapat mem-broadcast event ke state yang sedang aktif
     */
    gameDisplay.getDisplayCanvas.addMouseListener(GameStateManager)
    gameDisplay.getDisplayCanvas.addKeyListener(GameStateManager)
    gameDisplay.getDisplayCanvas.addMouseMotionListener(GameStateManager)

    /* Menentukan strategy buffering pada game */
    bufferStrategy = gameDisplay.getDisplayCanvas.getBufferStrategy
    if(bufferStrategy == null){
      /* Triple buffering */
      gameDisplay.getDisplayCanvas.createBufferStrategy(3)
      bufferStrategy = gameDisplay.getDisplayCanvas.getBufferStrategy
    }
    graphics = bufferStrategy.getDrawGraphics
  }

  /**
   * Meng-instansiasi state-state yang digunakan dalam game dan
   * juga meregister semua asset yang akan digunakan dalam game
   */
  private def initComponents : Unit = {
    /* Meregister asset background dan efek blur pada latar belakang */
    var backgroundBlurPrimary = Color.WHITE
    AssetManager.registerAsset("BackgroundWood",ImageIO.read(
      this.getClass().getResourceAsStream(ResourcePath.BACKGROUND_WOOD_PATH))
    )
    AssetManager.registerAsset("BackgroundBlur",new Color(
      backgroundBlurPrimary.getRed,
      backgroundBlurPrimary.getGreen,
      backgroundBlurPrimary.getBlue,
      120
    ))

    /* Meregister Warna-warna tambahan yang digunakan pada game */
    AssetManager.registerAsset("OliveColor", new Color(128,128,0))
    AssetManager.registerAsset("BlueVioletColor", new Color(138,43,226))

    /* Meregister Texture ular*/
    AssetManager.registerAsset("SnakeTexture",ImageIO.read(
      this.getClass().getResourceAsStream(ResourcePath.SNAKE_TEXTURE_PATH))
    )

    /* Meregister font yang digunakan*/
    var defaultFont : Font = Font.createFont(Font.TRUETYPE_FONT,
      this.getClass().getResourceAsStream(ResourcePath.DEFAULT_FONT_PATH)
      )
    AssetManager.registerAsset("DefaultFont",defaultFont.deriveFont(Font.PLAIN,15),
      graphics.getFontMetrics(defaultFont.deriveFont(Font.PLAIN,15)))

    /* Meregister warna background untuk board */
    AssetManager.registerAsset("BoardBackgroundColor",new Color(184,115,51,180))

    /* Meregister warna tile ganjil */
    AssetManager.registerAsset("TileColorOdd",Color.YELLOW)

    /* Meregister warna tile genap */
    AssetManager.registerAsset("TileColorEven",Color.BLACK)

    /* Meregister warna bayangan*/
    AssetManager.registerAsset("PrimaryShadowColor",new Color(Color.black.getRed,Color.black.getGreen,Color.black.getBlue,50))
    AssetManager.registerAsset("SecondaryShadowColor",new Color(Color.green.getRed,Color.green.getGreen,Color.green.getBlue,100))

    /* Meregister warna untuk tombol*/
    AssetManager.registerAsset("ButtonTitleColor", Color.BLACK)
    AssetManager.registerAsset("ButtonFaceColor", Color.WHITE)

    /* Meregister warna untuk label */
    var LabelPrimaryColor = Color.WHITE
    AssetManager.registerAsset("LabelColor", new Color(
      LabelPrimaryColor.getRed,
      LabelPrimaryColor.getGreen,
      LabelPrimaryColor.getBlue,
      200
    ))
    AssetManager.registerAsset("LabelTitleColor", Color.BLACK)

    /* Meregister warna untuk checkbox */
    AssetManager.registerAsset("CheckboxTitleColor", Color.BLACK)
    AssetManager.registerAsset("CheckboxCheckedColor", Color.GREEN)
    AssetManager.registerAsset("CheckboxUnCheckedColor", Color.WHITE)

    /* Meregister warna untuk panel */
    AssetManager.registerAsset("PanelColor",new Color(184,115,51,180))

    /* Meregister gambar untuk dadu */
    for(i <- 1 to 6){
      var diceName : String = String.format(DiceSettings.DICE_NAME_FORMAT.toString,i.asInstanceOf[Object])
      var dicePath : String = String.format(ResourcePath.DICE_IMAGES_PATH_FORMAT,i.asInstanceOf[Object])
      AssetManager.registerAsset(diceName,ImageIO.read(
        this.getClass.getResource(dicePath))
      )
    }

    /* Meregister warna untuk dadu */
    var DicePrimaryColor = Color.YELLOW
    AssetManager.registerAsset("DiceColor",new Color(
      DicePrimaryColor.getRed,
      DicePrimaryColor.getGreen,
      DicePrimaryColor.getBlue,
      200
    ))

    /* Menginstansiasi state PrePlayState */
    val mainMenuState : GameState = new MainMenuState("MainMenuState",this)
    GameStateManager.addState(mainMenuState)
    val prePlayState : GameState = new PrePlayState("PrePlayState")
    GameStateManager.addState(prePlayState)
    val settingsState : GameState = new SettingsState("SettingsState")
    GameStateManager.addState(settingsState)
    val aboutState : GameState = new AboutState("AboutState")
    GameStateManager.addState(aboutState)

    GameStateManager.setActiveState("MainMenuState")

  }

  /**
   * Merender dan mengupdate (running state) state yang aktif
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

  /**
   * Mengaktifkan antialiasing
   */
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
      System.exit(0)
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

