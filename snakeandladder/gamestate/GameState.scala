package snakeandladder.gamestate

import java.awt.Graphics
import java.util.ArrayList
import java.util.Iterator

import snakeandladder.gameevent.{GameEvent, GameEventListener, GameEventBroadcaster}
import snakeandladder.gameobject.{GameObjectUpdate, GameObject}

/**
 * Kelas yang mengatur bagaimana sebuah state dalam game di render, serta mengatur semua event yang terjadi dalam state
 */
class GameState extends GameEventBroadcaster with GameEventListener {
  /**
   * Menyimpan semua objek yang dapat dirender
   */
  private val renderableObjects : ArrayList[GameObject] = new ArrayList[GameObject]
  /**
   * Menyimpan semua objek yang dapat diupdate
   */
  private val updatableObjects : ArrayList[GameObjectUpdate] = new ArrayList[GameObjectUpdate]

  /**
   * Merender semua objek yang dapat di render
   * @param graphics objek Graphics
   */
  def renderObjects(graphics : Graphics) : Unit = {
    var iterator : Iterator[GameObject] = renderableObjects.iterator()
    while(iterator.hasNext){
      iterator.next().render(graphics)
    }
  }

  /**
   * Mengupdate semua objek yang dapat di render
   */
  def updateObjects : Unit = {
    var iterator : Iterator[GameObjectUpdate] = updatableObjects.iterator()
    while(iterator.hasNext){
      iterator.next().update()
    }
  }

  /**
   * Menambah komponen pada state
   * @param gameObject
   */
  def addComponentObject(gameObject : GameObject): Unit = {
    renderableObjects.add(gameObject)
    if(gameObject.isInstanceOf[GameObjectUpdate]){
      updatableObjects.add(gameObject.asInstanceOf[GameObjectUpdate])
    }
    if(gameObject.isInstanceOf[GameEventListener]){
      listOfListener.add(gameObject.asInstanceOf[GameEventListener])
    }
  }

  /**
   * Menambah banyak komponen sekaligus
   * @param gameObjects array dari komponen
   */
  def addComponentObjects(gameObjects : Array[GameObject]): Unit = {
    for(i <- 0 until gameObjects.size){
      addComponentObject(gameObjects(i))
    }
  }

  /**
   * Method yang akan dijalankan ketika event di kirim dari GameStateManager
   * @param event
   */
  override def gameEventTriggered(event: GameEvent): Unit = {
    triggerGameEvent(event)
  }
}
