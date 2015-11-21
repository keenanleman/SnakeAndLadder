package snakeandladder.engine

import java.awt.{BasicStroke, Stroke, Color}

import snakeandladder.utility.UniversalPath

object GameEngineSettings{
  /**
   * pengaturan antialias 
   */
  private var USE_ANTIALIAS : Boolean = true
  /**
   * pengaturan FPS atau frekuensi update
   */
  private var DEFAULT_FPS : Int = 60
  /**
   * pengaturan waktu yang dibutuhkan untuk
   * melakukan satu kali update atau
   * perioda update
   */
  private var DEFAULT_UPDATE_PERIOD : Double = 1000000000 / GameEngineSettings.DEFAULT_FPS
  /**
   * Merubah pengaturan antialiasing
   * @param value true untuk menggunakan antialiasing dan false jika sebaliknya
   */
  def setAntialiasing(value : Boolean) : Unit = {
    USE_ANTIALIAS = value
  }
  /**
   * Merubah pengaturan FPS
   * @param fps FPS yang akan di set
   */
  def setDefaultFPS(fps : Int) : Unit = {
    DEFAULT_FPS = fps
    DEFAULT_UPDATE_PERIOD = 1000000000 / DEFAULT_FPS
  }

  /**
   * Mengembalikan perioda update
   * @return perioda update
   */
  def getDefaultUpdatePeriod : Double = DEFAULT_UPDATE_PERIOD
  /**
   * Mengembalikan pengaturan antialiasing
   * @return pengaturan antialiasing
   */
  def getAntialiasSettings : Boolean = USE_ANTIALIAS
  /**
   * Mengembalikan pengaturan FPS
   * @return pengaturan FPS
   */
  def getDefaultFPS : Int = DEFAULT_FPS
}

object GameDisplaySettings{
  /**
   * Judul jendela game
   */
  val TITLE : String = "Snake and Ladder"
  /**
   * Ukuran lebar jendela game
   */
  val WINDOW_WIDTH : Int = 800
  /**
   * Ukuran tinggi jendela game
   */
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
  val ROLL_TIME_IN_SECONDS : Int = 1 * GameEngineSettings.getDefaultFPS

  /**
   * String format untuk file gambar dadu
   */
  val DICE_NAME_FORMAT = "Dice%d"

}

object PlayerSettings{
  /**
   * Ukuran player pada board
   */
  val PLAYER_SIZE : Double = 32
  /*
   * Kecepatan gerakan player pada board
   */
  val MOVE_SPEED : Double = 4
  /**
   * Posisi player relatif terhadap tile
   */
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
  /**
   * Tinggi dari tombol
   */
  val BUTTON_HEIGHT : Double = 30
  /**
   * Lebar dari tombol
   */
  val BIG_BUTTON_HEIGHT : Double = 50
  /**
   * Tinggi tombol kecil
   */
  val SMALL_BUTTON_HEIGHT : Double = 20
  /**
   * Ukuran dari checkbox
   */
  val CHECKBOX_BUTTON_SIZE : Double = 20
}

object ResourcePath{
  /**
   * Path file untuk gambar background main menu
   */
  val MAIN_BACKGROUND_PATH : String = new UniversalPath("/main_background.png").toString
  /**
   * Path file untuk gambar background
   */
  val BACKGROUND_WOOD_PATH : String = new UniversalPath("/wood_texture.jpg").toString
  /**
   * Path file untuk font
   */
  val DEFAULT_FONT_PATH : String =  new UniversalPath("/JosefinSans-SemiBold.ttf").toString
  /**
   * String format path file untuk dadu
   */
  val DICE_IMAGES_PATH_FORMAT : String = new UniversalPath("/Dice-0%d.png").toString
  /**
   * Path file untuk texture ular
   */
  val SNAKE_TEXTURE_PATH : String = new UniversalPath("/snake_texture.png").toString
}

object LabelSettings{
  /**
   * Tinggi dari label
   */
  val LABEL_HEIGHT : Double = 30
}

object GameRulesSettings{
  /**
   * Jumlah maksimum player
   */
  val MAX_PLAYER = 8
  /**
   * Jumlah maksimum tangga
   */
  val MAX_LADDER = 10
  /**
   * Jumlah maksumum ular
   */
  val MAX_SNAKE = 10

  /**
   * Jumlah minimum player
   */
  val MIN_PLAYER = 1
  /**
   * Jumlah minimum tangga
   */
  val MIN_LADDER = 4
  /**
   * Jumlah minimum ular
   */
  val MIN_SNAKE = 4
}

object LadderSettings{
  /**
   * Jarak antar anak-tangga
   */
  val LADDER_GAP = 25
}
