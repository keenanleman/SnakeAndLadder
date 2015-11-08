package snakeandladder.gameobject

/**
 * trait GameObjectEvent digunakan sebagai trait yang memberikan kemampuan GameObject untuk
 * melakukan update
 */
trait GameObjectEvent {
  /**
   * Method update yang harus di implementasikan semua GameObject yang mampu mengupdate
   * data
   */
  def update()
}
