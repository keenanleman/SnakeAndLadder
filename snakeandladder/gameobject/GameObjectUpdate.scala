package snakeandladder.gameobject

import snakeandladder.gameevent.GameEvent

/**
 * trait GameObjectEvent digunakan sebagai trait yang memberikan kemampuan GameObject untuk
 * melakukan update
 */
trait GameObjectUpdate{
  /**
   * Method update yang harus di implementasikan semua GameObject yang mampu mengupdate
   * data
   */
  def update()
}
