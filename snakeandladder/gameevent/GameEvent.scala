package snakeandladder.gameevent

import java.util.EventObject

class GameEvent(source : Object, event : EventObject) extends EventObject(source) {
  def getEventObject : EventObject = event
}
