package snakeandladder.gameobject

import java.util.Random

import snakeandladder.engine.GameEngine

class Dice(initialX : Double, initialY : Double) extends GameObject(initialX, initialY) with GameObjectEvent{
  private var currentValue : Int = 1
  private var diceUpdateDelayer	: Int = 0
  private var rollDelayer : Int = 0
  private var rolled : Boolean = false

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

}

object Dice{
  val ROLL_TIME : Int = 2 * GameEngine.DEFAULT_FPS
}