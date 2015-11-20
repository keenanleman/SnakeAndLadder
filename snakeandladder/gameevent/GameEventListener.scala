package snakeandladder.gameevent

/**
 * Interface yang memberikan kemampuan objek untuk mendengarkan atau menerima GameEvent
 */
trait GameEventListener {
  /**
   * Method yang akan dipanggil ketika suatu event di broadcast
   * @param event event yand di broadcast
   */
  def gameEventTriggered(event : GameEvent)
}
