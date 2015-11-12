package snakeandladder.gamestate

import java.awt.Graphics
import java.util.ArrayList
import java.util.Iterator

import snakeandladder.gameevent.{GameEvent, GameEventListener, GameEventBroadcaster}
import snakeandladder.gameobject.{GameObjectUpdate, GameObject}

class GameState extends GameEventBroadcaster with GameEventListener {
  private val renderableObjects : ArrayList[GameObject] = new ArrayList[GameObject]
  private val updatableObjects : ArrayList[GameObjectUpdate] = new ArrayList[GameObjectUpdate]

  def renderObjects(graphics : Graphics) : Unit = {
    var iterator : Iterator[GameObject] = renderableObjects.iterator()
    while(iterator.hasNext){
      iterator.next().render(graphics)
    }
  }

  def updateObjects : Unit = {
    var iterator : Iterator[GameObjectUpdate] = updatableObjects.iterator()
    while(iterator.hasNext){
      iterator.next().update()
    }
  }

  def addComponentObject(gameObject : GameObject): Unit = {
    renderableObjects.add(gameObject)
    if(gameObject.isInstanceOf[GameObjectUpdate]){
      updatableObjects.add(gameObject.asInstanceOf[GameObjectUpdate])
    }
    if(gameObject.isInstanceOf[GameEventListener]){
      listOfListener.add(gameObject.asInstanceOf[GameEventListener])
    }
  }

  def addComponentObjects(gameObjects : Array[GameObject]): Unit = {
    for(i <- 0 until gameObjects.size){
      addComponentObject(gameObjects(i))
    }
  }
  override def gameEventTriggered(event: GameEvent): Unit = {
    triggerGameEvent(event)
  }
}
