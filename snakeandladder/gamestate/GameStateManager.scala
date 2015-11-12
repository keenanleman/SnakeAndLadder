package snakeandladder.gamestate

import java.awt.Graphics
import java.awt.event.{KeyEvent, MouseEvent, MouseListener, KeyListener}
import java.util.HashMap

import snakeandladder.gameevent.{GameEvent, GameEventBroadcaster}

object GameStateManager extends KeyListener with MouseListener {
  private val stateList : HashMap[String, GameState]  = new HashMap[String, GameState]()
  private var activeState : GameState = null

  def addState(gameState : GameState, stateName : String): Unit ={
    stateList.put(stateName,gameState)
  }

  def setActiveState(stateName : String): Unit ={
    activeState = stateList.get(stateName)
  }

  def runState(graphics : Graphics): Unit = {
    if(activeState != null){
      activeState.renderObjects(graphics)
      activeState.updateObjects
    }
  }

  override def keyTyped(keyEvent: KeyEvent): Unit = {
    if(activeState != null){
      activeState.gameEventTriggered(new GameEvent(this,keyEvent))
    }
  }

  override def keyPressed(keyEvent: KeyEvent): Unit = {
    if(activeState != null){
      activeState.gameEventTriggered(new GameEvent(this,keyEvent))
    }
  }

  override def keyReleased(keyEvent: KeyEvent): Unit = {
    if(activeState != null){
      activeState.gameEventTriggered(new GameEvent(this,keyEvent))
    }
  }

  override def mouseExited(mouseEvent: MouseEvent): Unit = {
    if(activeState != null){
      activeState.gameEventTriggered(new GameEvent(this,mouseEvent))
    }
  }

  override def mouseClicked(mouseEvent: MouseEvent): Unit = {
    if(activeState != null){
      activeState.gameEventTriggered(new GameEvent(this,mouseEvent))
    }
  }

  override def mouseEntered(mouseEvent: MouseEvent): Unit = {
    if(activeState != null){
      activeState.gameEventTriggered(new GameEvent(this,mouseEvent))
    }
  }

  override def mousePressed(mouseEvent: MouseEvent): Unit = {
    if(activeState != null){
      activeState.gameEventTriggered(new GameEvent(this,mouseEvent))
    }
  }

  override def mouseReleased(mouseEvent: MouseEvent): Unit = {
    if(activeState != null){
      activeState.gameEventTriggered(new GameEvent(this,mouseEvent))
    }
  }
}
