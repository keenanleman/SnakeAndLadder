package snakeandladder.gameevent

import java.awt.event.MouseEvent
import java.util.EventObject

/**
 * Interface yang memberikan kemampuan objek untuk mendengarkan atau menerima MouseGameEvent
 */
trait MouseEventListener extends GameEventListener{
  /**
   * Method yang akan di panggil ketika event terjadi, dan akan meneruskan untuk memangil method
   * lain untuk meng-handle event dari mouse
   * @param event event yang terjadi
   */
  override def gameEventTriggered(event: GameEvent): Unit = {
    if(event.getRealEventObject.isInstanceOf[MouseEvent]){
      triggeredMouseEvent(event.getRealEventObject.asInstanceOf[MouseEvent])
    }
  }

  /**
   * Method yang akan dipanggil ketika event mouse terjadi
   * @param event event yang terjadi
   */
  def triggeredMouseEvent(event : MouseEvent)
}
