package snakeandladder.engine

import java.awt.{Canvas, Dimension}
import javax.swing.JFrame

/**
 * Game Display
 * menyiapkan media, Canvas dan JFrame
 */
class GameDisplay(){
  private val windowTitle : String = GameEngine.TITLE
  private val width : Int = GameEngine.WINDOW_WIDTH
  private val height : Int = GameEngine.WINDOW_HEIGHT
  private val windowDimension = new Dimension(width,height)
  /* JFrame */
  private val displayFrame : JFrame = new JFrame(windowTitle)
  /* Canvas */
  private val displayCanvas : Canvas = new Canvas()
  displayFrame.setSize(width,height)
  displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  displayFrame.setResizable(false)
  displayFrame.setLocationRelativeTo(null)
  displayCanvas.setPreferredSize(new Dimension(width,height))
  displayCanvas.setMaximumSize(new Dimension(width,height))
  displayCanvas.setMinimumSize(new Dimension(width,height))
  displayCanvas.setFocusable(true)
  displayFrame.add(displayCanvas)
  displayFrame.setVisible(true)
  displayFrame.pack()

  def getDisplayCanvas : Canvas = displayCanvas
}
