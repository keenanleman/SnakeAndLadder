package snakeandladder.gamecomponent

/**
 * Label yang turunkan khusus untuk menyimpan nilai integer
 * @param initialX posisi X
 * @param initialY posisi Y
 * @param title judul dari label
 */
class IntegerLabel(initialX : Double, initialY : Double, title : String)
  extends Label(initialX, initialY, title){

  /**
   * Method untuk menambah 1 nilai label
   */
  def increase : Unit = {
    setText((titleText.toInt + 1).toString)
  }

  /**
   * Method untuk mengurangi 1 nilai label
   */
  def decrease : Unit = {
    setText((titleText.toInt - 1).toString)
  }
}
