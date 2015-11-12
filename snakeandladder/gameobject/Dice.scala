package snakeandladder.gameobject

import java.awt.event.{InputEvent, MouseEvent}
import java.awt._
import java.awt.geom.{Point2D, RoundRectangle2D}
import java.util.Random
import javax.swing.SwingUtilities

import snakeandladder.engine.GameEngine
import snakeandladder.gameevent.{MouseEventListener}

class Dice(initialX : Double, initialY : Double) extends GameObject(initialX, initialY) with GameObjectUpdate{
  x+= Dice.DICE_POSTITION
  y+= Dice.DICE_POSTITION

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
  private var returnValueDice : Int = 0
  /**
    * nilai dadu sudah di kocok atau belum
    */
  private var rolled : Boolean = false
  /**
    * variable untuk menggambar dadu
    */
  private var diceDrawable : RoundRectangle2D.Double = new RoundRectangle2D.Double(x,y,Dice.DICE_SIZE,Dice.DICE_SIZE,20,20)
  /**
    * nilai dadu yang di random
    */
  private val randomDice = new Random(System.nanoTime())

  def roll : Unit = {
    rolled = true
    returnValueDice = 0
  }

  def update : Unit = {
    if (rolled) {
      rollDelayer += 1
      if (diceUpdateDelayer < Dice.ROLL_TIME) {
        diceUpdateDelayer += 1
        currentValue = randomDice.nextInt(6) + 1
      } else {
        diceUpdateDelayer = 0
        currentValue = randomDice.nextInt(6) + 1
        rolled = false
        returnValueDice = currentValue
      }
    }
  }

  def getCurrentValue : Int = returnValueDice

  def resetDice : Unit = {
    returnValueDice = 0
  }

  def getDrawable : Shape = diceDrawable

  /**
    * @param graphics graphics dari canvas pada kelas GameDisplay
    * method untuk merender dadu
    */
  override def render(graphics: Graphics): Unit ={
    var g2d : Graphics2D = graphics.asInstanceOf[Graphics2D]
    var diceColor = new Color(Color.RED.getRed, Color.RED.getGreen, Color.RED.getBlue, 200)
    g2d.setFont(new Font("default", Font.BOLD, 16))
    g2d.drawString(currentValue.toString,(x - 5 + Dice.DICE_SIZE/2).asInstanceOf[Float],(y + Dice.DICE_SIZE/2).asInstanceOf[Float])
    g2d.setColor(diceColor)
    g2d.fill(diceDrawable)
  }
}

object Dice{
  /**
    * Ukuran dadu
    */
  val DICE_SIZE : Double = 40
  /**
    * Menentukan posisi dadu
    */
  val DICE_POSTITION : Double = (Tile.TILE_SIZE - Player.PLAYER_SIZE) / 2
  /**
    * Durasi animasi perputaran dadu
    */
  val ROLL_TIME : Int = 2 * GameEngine.DEFAULT_FPS
}