package snakeandladder.gameevent

import java.awt.event.MouseEvent
import java.util.EventObject

trait MouseEventListener extends GameEventListener{
  override def gameEventTriggered(event: GameEvent): Unit = {
    if(event.getRealEventObject.isInstanceOf[MouseEvent]){
      triggeredMouseEvent(event.getRealEventObject.asInstanceOf[MouseEvent])
    }
  }

  def triggeredMouseEvent(event : MouseEvent)
}
