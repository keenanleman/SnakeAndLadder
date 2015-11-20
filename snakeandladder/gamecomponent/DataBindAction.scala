package snakeandladder.gamecomponent

/**
 * Interface yang digunakan sebagai objek
 * penentu aksi 'data-binding'
 */
trait DataBindAction{
  /**
   * Method yang akan di implementasikan
   * untuk mengupdate data
   */
  def updateData : Unit
}
