package snakeandladder.gameevent

/**
 * Interface yang memberikan kemampuan objek untuk mendengarkan atau menerima GameEvent
 */
trait GameEventListener {
  def gameEventTriggered(event : GameEvent)
}
