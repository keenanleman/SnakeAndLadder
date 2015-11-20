package snakeandladder.gamecomponent

import java.awt.geom.{Rectangle2D, Point2D, Ellipse2D}
import java.awt.{Font, Color, Graphics2D}
import java.awt.event.MouseEvent

import snakeandladder.engine.ButtonSettings
import snakeandladder.utility.AssetManager

/**
 * Kelas yang digunakan sebagai CheckBox dalam game
 * @param initialX posisi X
 * @param initialY posisi Y
 * @param title judul/text dari checkbox
 * @param width lebar dari checkbox
 * @param height tinggi dari checkbox
 */
class CheckBoxButton(initialX : Double, initialY : Double, title : String, width : Double,height : Double)
  extends ButtonComponent(initialX, initialY, title, width,height){

  /**
   * Ukuran checkbox
   */
  private val buttonSize : Double = ButtonSettings.CHECKBOX_BUTTON_SIZE

  /**
   * Ukuran bayangan checkbox
   */
  private val buttonShadowSize : Double = ButtonSettings.CHECKBOX_BUTTON_SIZE + 4

  /**
   * Warna dari judul checkbox
   */
  private val checkboxTitleColor : Color = AssetManager.getColor("CheckboxTitleColor")

  /**
   * Warna saat checkbox dalam keadaan checked
   */
  private val checkedColor : Color = AssetManager.getColor("CheckboxCheckedColor")

  /**
   * Warna saat checkbox dalam keadaan unchecked
   */
  private val unCheckedColor : Color = AssetManager.getColor("CheckboxUnCheckedColor")

  /**
   * Objek yang dirender sebagai checkbox
   */
  buttonDrawable = new Rectangle2D.Double(x,y,buttonSize,buttonSize)

  /**
   * Objek yang dirender sebagai bayangan checkbox
   */
  buttonShadowDrawable = new Rectangle2D.Double(x - 2, y - 2, buttonShadowSize, buttonShadowSize)

  /**
   * Objek yang dirender sebagai tanda checkbox dalam keadaan checked
   */
  private var buttonCheckMarkDrawable : Rectangle2D.Double =
    new Rectangle2D.Double(x, y, buttonSize,buttonSize)

  /**
   * Value dari checkbox
   */
  private var checked : Boolean = false

  /**
   * Mengeset nilai dari checkbox
   */
  def setValue(value : Boolean) : Unit = {
    checked = value
  }

  /**
   * Mengambil nilai dari checkbox
   * @return nilai dari label
   */
  def getValue : Boolean = checked

  /**
   * Merender title dari tombol
   * @param g2d objek yang digunakan untuk menggambar pada canvas
   */
  override protected def drawButtonTitle(g2d: Graphics2D): Unit = {
    g2d.setColor(checkboxTitleColor)
    g2d.setFont(buttonFont.deriveFont(Font.PLAIN,15))
    g2d.drawString(title, x.toFloat + 24 , y.toFloat + 28 / 2)
  }

  /**
   * Meng-apply efek saat tombol di tekan
   * @param event event yang terjadi
   */
  override protected def buttonClickedEffect(event: MouseEvent): Unit = {
    checked = !checked
  }

  /**
   * Merender tombol
   * @param g2d Objek untuk menggambar pada canvas
   */
  override def drawButton(g2d : Graphics2D) : Unit = {
    if(checked){
      g2d.setColor(checkedColor)
    }else{
      g2d.setColor(unCheckedColor)
    }
    g2d.fill(buttonDrawable)
  }

  /**
   * Meng-apply efek saat tombol di lepas
   */
  override protected def buttonReleaseEffect: Unit = {
    //Not implemented
  }
}
