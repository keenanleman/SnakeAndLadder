package snakeandladder.engine

import java.awt.{BasicStroke, Stroke, Color}

import snakeandladder.utility.UniversalPath

object GameEngineSettings{
  /* Mutable */
  private var USE_ANTIALIAS : Boolean = true
  private var DEFAULT_FPS : Int = 60
  private var DEFAULT_UPDATE_PERIOD : Double = 1000000000 / GameEngineSettings.DEFAULT_FPS
  /* Mutator */
  def setAntialiasing(value : Boolean) : Unit = {
    USE_ANTIALIAS = value
  }
  def setDefaultFPS(fps : Int) : Unit = {
    DEFAULT_FPS = fps
    DEFAULT_UPDATE_PERIOD = 1000000000 / DEFAULT_FPS
  }
  /* Accessor */
  def getDefaultUpdatePeriod : Double = DEFAULT_UPDATE_PERIOD
  def getAntialiasSettings : Boolean = USE_ANTIALIAS
  def getDefaultFPS : Int = DEFAULT_FPS
}

object GameDisplaySettings{
  val TITLE : String = "Snake and Ladder"
  val WINDOW_WIDTH : Int = 800
  val WINDOW_HEIGHT : Int = 600
}

object DiceSettings{
  /**
   * Ukuran dadu
   */
  val DICE_SIZE : Double = 150
  /**
   * Durasi animasi perputaran dadu
   */
  val ROLL_TIME_IN_SECONDS : Int = 2 * GameEngineSettings.getDefaultFPS

  val DICE_NAME_FORMAT = "Dice%d"

}

object PlayerSettings{
  /* Ukuran player pada board*/
  val PLAYER_SIZE : Double = 32
  /* Kecepatan gerakan player pada board */
  val MOVE_SPEED : Double = 4
  /* Posisi player relatif terhadap tile*/
  val PLAYER_REL_POS_TO_TILE : Double = (BoardSettings.TILE_SIZE - PLAYER_SIZE) / 4
}
object BoardSettings{
    /**
     * Ukuran tile
     */
    val TILE_SIZE : Int = 52
    /**
     * Jumlah warna tile
     */
    val TILE_COLOR_NUM = 4
    /**
     * Warna tile bernomor ganjil
     */
    val TILE_COLOR_ODD = Color.YELLOW
    /**
     * Warna tile bernomor genap
     */
    val TILE_COLOR_EVEN = Color.BLACK
}

object SnakeSettings{
  /**
   * Panjang gelombang ular / lamda
   */
  val CURVE_GAP : Int = 50
  /**
   * Bilangan pengali untuk  jarak terdekat titik-titik bezier dari titik seimbang gelombang
   */
  val BEZIER_POWER : Int = 1
  /**
   * Lebar dari garis pembentuk badan ular
   */
  val SNAKE_STROKE : Stroke = new BasicStroke(5)
  /**
   * Lebar dari kepala ular
   */
  val SNAKE_HEAD_SIZE : Double = 12
}

object ButtonSettings{
  val BUTTON_HEIGHT : Double = 30
  val BIG_BUTTON_HEIGHT : Double = 50
  val SMALL_BUTTON_HEIGHT : Double = 20
  val CHECKBOX_BUTTON_SIZE : Double = 20
}

object ResourcePath{
  val BACKGROUND_WOOD_PATH : String = new UniversalPath("src/resources/wood_texture.jpg").toString
  println(BACKGROUND_WOOD_PATH)
  val DEFAULT_FONT_PATH : String =  new UniversalPath("src/resources/JosefinSans-SemiBold.ttf").toString
  val DICE_IMAGES_PATH_FORMAT : String = new UniversalPath("src/resources/Dice-0%d.png").toString
  val SNAKE_TEXTURE_PATH : String = new UniversalPath("src/resources/snake_texture.png").toString
}

object LabelSettings{
  val LABEL_HEIGHT : Double = 30
}

object GameRulesSettings{
  val MAX_PLAYER = 8
  val MAX_LADDER = 10
  val MAX_SNAKE = 10

  val MIN_PLAYER = 2
  val MIN_LADDER = 4
  val MIN_SNAKE = 4
}