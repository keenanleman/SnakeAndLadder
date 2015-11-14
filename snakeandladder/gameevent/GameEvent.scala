package snakeandladder.gameevent

import java.util.EventObject

/**
 * Kelas yang menjadi event pada game
 * @param source Objek yang menghasilkan event
 * @param event
 */
class GameEvent(source : Object, event : EventObject) {
  /**
   * Mengembalikan event asli dari java
   * @return
   */
  def getRealEventObject : EventObject = event
}
