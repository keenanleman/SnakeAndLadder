package snakeandladder.engine

import java.awt.{Canvas, Dimension}
import java.util.EventListener
import javax.swing.JFrame

/**
 * Game Display
 * menyiapkan media, Canvas dan JFrame
 */
class GameDisplay(){
  private val windowTitle : String = GameDisplaySettings.TITLE
  private val width : Int = GameDisplaySettings.WINDOW_WIDTH
  private val height : Int = GameDisplaySettings.WINDOW_HEIGHT
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

  def registerDirectEvent(eventListener : EventListener): Unit ={

  }

  def getDisplayCanvas : Canvas = displayCanvas
}
