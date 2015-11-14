package snakeandladder.gameobject

import java.awt._
import java.awt.geom.{RoundRectangle2D}
import java.util.Random

import snakeandladder.engine.{GameDisplaySettings, DiceSettings}

class Dice(initialX : Double, initialY : Double) extends GameObject(initialX, initialY) with GameObjectUpdate{
  x+= DiceSettings.DICE_POSTITION
  y+= DiceSettings.DICE_POSTITION

  /**
    * nilai pada mata dadu
    */
  private var currentValue : Int = 1
  /**
    * delayer untuk merubah nilai dadu
    */
  private var diceUpdateDelayer	: Int = 0
  /**
    * delayer untuk mengocok dadu
    */
  private var rollDelayer : Int = 0
  /**
   * Nilai dari dadu yang akan dikembalikan
   */
  private var returnValueDice : Int = 0
  /**
   * Menjadi negator untuk gerakan mengocok dadu
   */
  private var shakeCtrl : Boolean = false
  /**
    * nilai dadu sudah di kocok atau belum
    */
  private var rolled : Boolean = false
  /**
    * variable untuk menggambar dadu
    */
  private var diceDrawable : RoundRectangle2D.Double =
    new RoundRectangle2D.Double(x,y,DiceSettings.DICE_SIZE,DiceSettings.DICE_SIZE,20,20)
  /**
    * nilai dadu yang di random
    */
  private val randomDice = new Random(System.nanoTime())

  /**
   * Mengocok dadu
   */
  def roll : Unit = {
    rolled = true
    returnValueDice = 0
  }

  /**
   * Method update yang harus di implementasikan semua GameObject yang mampu mengupdate
   * data
   */
  override def update : Unit = {
    if (rolled) {
      rollDelayer += 1
      if (diceUpdateDelayer < DiceSettings.ROLL_TIME_IN_SECONDS) {
        diceUpdateDelayer += 1
        currentValue = randomDice.nextInt(6) + 1
        if(shakeCtrl){
          diceDrawable.x += 3
          shakeCtrl = false
        }else{
          diceDrawable.x -= 3
          shakeCtrl = true
        }
      } else {
        diceUpdateDelayer = 0
        currentValue = randomDice.nextInt(6) + 1
        rolled = false
        returnValueDice = currentValue
      }
    }
  }

  /**
   * Mengembalikan nilai dadu
   * @return
   */
  def getCurrentValue : Int = returnValueDice

  /**
   * Mengeset nilai dadu yang menjadi nilai kembalian, kembali menjadi 0
   */
  def resetDice : Unit = {
    returnValueDice = 0
  }

  /**
   * Mengembalikan objek gambar dari dadu
   * @return
   */
  def getDrawable : Shape = diceDrawable

  /**
    * @param graphics graphics dari canvas pada kelas GameDisplay
    * method untuk merender dadu
    */
  override def render(graphics: Graphics): Unit ={
    var g2d : Graphics2D = graphics.asInstanceOf[Graphics2D]
    var diceColor = new Color(Color.RED.getRed, Color.RED.getGreen, Color.RED.getBlue, 200)
    g2d.setFont(new Font("default", Font.BOLD, 20))
    g2d.drawString(currentValue.toString,(diceDrawable.x - 7 + DiceSettings.DICE_SIZE/2).asInstanceOf[Float],(diceDrawable.y + 5 + DiceSettings.DICE_SIZE/2).asInstanceOf[Float])
    g2d.setColor(diceColor)
    g2d.fill(diceDrawable)
  }
}

