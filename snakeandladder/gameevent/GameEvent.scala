package snakeandladder.gameevent

import java.util.EventObject

class GameEvent(source : Object, event : EventObject) {
  def getRealEventObject : EventObject = event
}
