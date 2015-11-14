package snakeandladder.gamecomponent

/**
 * Kelas yang menentukan aksi saat tombol ditekan
 */
abstract class ButtonAction {
  /**
   * Method yang akan dipanggil saat tombol ditekan
   */
  def buttonClicked : Unit
}
