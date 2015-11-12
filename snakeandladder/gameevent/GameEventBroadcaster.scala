package snakeandladder.gameevent

import java.util.{EventObject, ArrayList, Iterator}

trait GameEventBroadcaster {
  protected val listOfListener : ArrayList[GameEventListener] = new ArrayList[GameEventListener]()

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

  def triggerGameEvent(event : GameEvent): Unit = {
    var listenerIterator : Iterator[GameEventListener] =  listOfListener.iterator()
    while(listenerIterator.hasNext){
      listenerIterator.next().gameEventTriggered(new GameEvent(this,event.getRealEventObject))
    }
  }
}
