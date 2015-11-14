package snakeandladder.gameevent

import java.util.{EventObject, ArrayList, Iterator}

/**
 * Merupakan interface yang memberikan kemampuan objek untuk mem-broadcast suatu event
 */
trait GameEventBroadcaster {
  /**
   * List dari listener
   */
  protected val listOfListener : ArrayList[GameEventListener] = new ArrayList[GameEventListener]()

  /**
   * Menambah objek yang akan mendengarkan event dari objek ini
   * @param gameEventListener objek yang akan ditambahkan
   */
  def addGameListener(gameEventListener: GameEventListener) : Unit = {
    synchronized(
      listOfListener.add(gameEventListener)
    )
  }

  /**
   * Menghapus objek yang akan mendengarkan event dari objek ini
   * @param gameEventListener objek yang akan dihapus
   */
  def removeGameListener(gameEventListener: GameEventListener) : Unit = {
    synchronized(
      listOfListener.remove(gameEventListener)
    )
  }

  /**
   * Mem-broadcast event pada semua listener
   * @param event event yang akan di broadcast
   */
  def triggerGameEvent(event : GameEvent): Unit = {
    var listenerIterator : Iterator[GameEventListener] =  listOfListener.iterator()
    synchronized(
      while(listenerIterator.hasNext){
        listenerIterator.next().gameEventTriggered(new GameEvent(this,event.getRealEventObject))
      }
    )
  }
}
