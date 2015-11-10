package snakeandladder.gameevent

import java.util.{EventObject, ArrayList, Iterator}

trait GameEventBroadcaster {
  private val listOfListener : ArrayList[GameEventListener] = new ArrayList[GameEventListener]()

  def addGameListener(gameEventListener: GameEventListener) : Unit = {
    synchronized(
      listOfListener.add(gameEventListener)
    )
  }

  def removeGameListener(gameEventListener: GameEventListener) : Unit = {
    synchronized(
      listOfListener.remove(gameEventListener)
    )
  }

  def triggerGameEvent(event : EventObject): Unit = {
    var listenerIterator : Iterator[GameEventListener] =  listOfListener.iterator()
    while(listenerIterator.hasNext){
      listenerIterator.next().gameEventTriggered(new GameEvent(this,event))
    }
  }
}
