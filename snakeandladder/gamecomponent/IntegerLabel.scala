package snakeandladder.gamecomponent

class IntegerLabel(initialX : Double, initialY : Double, title : String)
  extends Label(initialX, initialY, title){
  def increase : Unit = {
    setText((titleText.toInt + 1).toString)
  }

  def decrease : Unit = {
    setText((titleText.toInt - 1).toString)
  }
}
