package snakeandladder.engine

import java.awt.{Canvas, Dimension}
import javax.swing.JFrame

/**
 * Game Display
 * menyiapkan media, Canvas dan JFrame
 */
class GameDisplay(){
  /**
   * Judul untuk jendela game
   */
  private val windowTitle : String = GameDisplaySettings.TITLE
  /**
   * Lebar untuk jendela game
   */
  private val width : Int = GameDisplaySettings.WINDOW_WIDTH
  /**
   * Tinggi untuk jendela game
   */
  private val height : Int = GameDisplaySettings.WINDOW_HEIGHT
  /**
   * Dimensi dari jendela game
   */
  private val windowDimension = new Dimension(width,height)
  /**
   * Frame/Window yang digunakan dalam game
   */
  private val displayFrame : JFrame = new JFrame(windowTitle)
  /**
   * Canvas yang digunakan untuk merender komponen-komponen dalam game
   */
  private val displayCanvas : Canvas = new Canvas()
  /* mengeset ukuran jendela */
  displayFrame.setSize(width,height)
  /* mengeset operasi yang dilakukan saat tombol close ditekan */
  displayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
  /* mengeset jendela agar tidak dapat di resize */
  displayFrame.setResizable(false)
  /* mengeset jendela agar tidak dapat di resize */
  displayFrame.setLocationRelativeTo(null)
  /* mengeset ukuran canvas */
  displayCanvas.setPreferredSize(new Dimension(width,height))
  displayCanvas.setMaximumSize(new Dimension(width,height))
  displayCanvas.setMinimumSize(new Dimension(width,height))
  displayCanvas.setFocusable(true)
  displayFrame.add(displayCanvas)
  displayFrame.setVisible(true)
  displayFrame.pack()

  /**
   * Mengembalikan canvas yang digunakan untuk merender GameObject
   */
  def getDisplayCanvas : Canvas = displayCanvas
}
