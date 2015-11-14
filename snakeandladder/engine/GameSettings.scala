package snakeandladder.engine

import java.awt.{BasicStroke, Stroke, Color}

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
  val WINDOW_HEIGHT : Int = 800
}

object DiceSettings{
  /**
   * Ukuran dadu
   */
  val DICE_SIZE : Double = 40
  /**
   * Menentukan posisi dadu
   */
  val DICE_POSTITION : Double = (BoardSettings.TILE_SIZE - PlayerSettings.PLAYER_SIZE) / 2
  /**
   * Durasi animasi perputaran dadu
   */
  val ROLL_TIME_IN_SECONDS : Int = 2 * GameEngineSettings.getDefaultFPS
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
    val TILE_SIZE : Int = 64
    /**
     * Jumlah warna tile
     */
    val TILE_COLOR_NUM = 4
    /**
     * Warna tile bernomor ganjil
     */
    val TILE_COLOR_ODD = Color.GRAY
    /**
     * Warna tile bernomor genap
     */
    val TILE_COLOR_EVEN = Color.WHITE
}

object SnakeSettings{
  /**
   * Panjang gelombang ular / lamda
   */
  val CURVE_GAP : Int = 60
  /**
   * Bilangan pengali untuk  jarak terdekat titik-titik bezier dari titik seimbang gelombang
   */
  val BEZIER_POWER : Int = 1
  /**
   * Lebar dari garis pembentuk badan ular
   */
  val SNAKE_STROKE : Stroke = new BasicStroke(8)
  /**
   * Lebar dari kepala ular
   */
  val SNAKE_HEAD_WIDTH : Double = 25
  /**
   * Tinggi dari kepala ular
   */
  val SNAKE_HEAD_HEIGHT : Double = 15
}

object ButtonSettings{
  val BUTTON_HEIGHT : Double = 30
}
