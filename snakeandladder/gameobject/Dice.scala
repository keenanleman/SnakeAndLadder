package snakeandladder.gameobject

import java.awt.{Color, Graphics2D, Graphics}
import java.awt.geom.RoundRectangle2D
import java.util.Random

import snakeandladder.engine.GameEngine

class Dice(initialX : Double, initialY : Double) extends GameObject(initialX, initialY) with GameObjectEvent{
  x+= Dice.DICE_POSTITION
  y+= Dice.DICE_POSTITION

  private var currentValue : Int = 1
  private var diceUpdateDelayer	: Int = 0
  private var rollDelayer : Int = 0
  private var rolled : Boolean = false

  private var diceDrawable : RoundRectangle2D.Double = new RoundRectangle2D.Double(x,y,Dice.DICE_SIZE,Dice.DICE_SIZE,20,20)

  private val randomDice = new Random(System.nanoTime())

  def roll : Int = {
    rolled = true
    while(rollDelayer < Dice.ROLL_TIME){
      if(!(rollDelayer < Dice.ROLL_TIME)) {
        rollDelayer = 0
      }
    }
    return currentValue
  }

  override def update () : Unit = {
    if(rolled){
      rollDelayer += 1
      if(diceUpdateDelayer < GameEngine.DEFAULT_FPS / 2){
        diceUpdateDelayer += 1
      }else {
        diceUpdateDelayer = 0
        currentValue = randomDice.nextInt(6) + 1
      }
    }
  }

  override def render(graphics: Graphics): Unit ={
    var g2d : Graphics2D = graphics.asInstanceOf[Graphics2D]
    var diceColor = new Color(Color.RED.getRed, Color.RED.getGreen, Color.RED.getBlue, 200)
    g2d.setColor(diceColor)
    g2d.fill(diceDrawable)
  }

}

object Dice{
  val DICE_SIZE : Double = 40
  val DICE_POSTITION : Double = (Tile.TILE_SIZE - Player.PLAYER_SIZE) / 2
  val ROLL_TIME : Int = 2 * GameEngine.DEFAULT_FPS
}