package snakeandladder.gamestate

import java.awt.Graphics
import java.awt.event._
import java.util.HashMap

import snakeandladder.gameevent.{GameEvent, GameEventBroadcaster}

/**
 * Sebuah kelas statik yang berguna untuk memanage state-state yang ada dalam game
 */
object GameStateManager extends KeyListener with MouseListener with MouseMotionListener{
  /**
   * List dari state yang ada
   */
  private val stateList : HashMap[String, GameState]  = new HashMap[String, GameState]()
  /**
   * State yang sedang aktif
   */
  private var activeState : GameState = null

  /**
   * Mengambah state kedalam list
   * @param gameState state yang akan ditambahkan
   * @param stateName nama dari state
   */
  def addState(gameState : GameState, stateName : String): Unit ={
    stateList.put(stateName,gameState)
  }

  /**
   * Mengaktifkan sebuah state
   * @param stateName nama dari state
   */
  def setActiveState(stateName : String): Unit ={
    activeState = stateList.get(stateName)
  }

  /**
   * Merender dan mengupdate state yang aktif
   * @param graphics
   */
  def runState(graphics : Graphics): Unit = {
    if(activeState != null){
      activeState.renderObjects(graphics)
      activeState.updateObjects
    }
  }

  /**
   * Mem-broadcast ke state yang aktif bahwa sebuah key di tekan
   * @param keyEvent
   */
  override def keyTyped(keyEvent: KeyEvent): Unit = {
    if(activeState != null){
      activeState.gameEventTriggered(new GameEvent(this,keyEvent))
    }
  }

  /**
   * Mem-broadcast ke state yang aktif bahwa sebuah key di tekan
   * @param keyEvent
   */
  override def keyPressed(keyEvent: KeyEvent): Unit = {
    if(activeState != null){
      activeState.gameEventTriggered(new GameEvent(this,keyEvent))
    }
  }

  /**
   * Mem-broadcast ke state yang aktif bahwa sebuah key di lepas
   * @param keyEvent
   */
  override def keyReleased(keyEvent: KeyEvent): Unit = {
    if(activeState != null){
      activeState.gameEventTriggered(new GameEvent(this,keyEvent))
    }
  }


  /**
   * Mem-broadcast ke state yang aktif bahwa mouse bergerak
   * @param mouseEvent event mouse
   */
  override def mouseMoved(mouseEvent: MouseEvent): Unit = {
    if(activeState != null){
      activeState.gameEventTriggered(new GameEvent(this,mouseEvent))
    }
  }

  /**
   * Mem-broadcast ke state yang aktif bahwa mouse ditekan
   * @param mouseEvent event mouse
   */
  override def mousePressed(mouseEvent: MouseEvent): Unit = {
    if(activeState != null){
      activeState.gameEventTriggered(new GameEvent(this,mouseEvent))
    }
  }

  override def mouseEntered(mouseEvent: MouseEvent): Unit = {
    // Not implemented
  }

  override def mouseExited(mouseEvent: MouseEvent): Unit = {
    // Not implemented
  }

  override def mouseReleased(mouseEvent: MouseEvent): Unit = {
    // Not implemented
  }

  override def mouseDragged(mouseEvent: MouseEvent): Unit = {
    // Not implemented
  }
  override def mouseClicked(mouseEvent: MouseEvent): Unit = {
    // Not implemented
  }


}
