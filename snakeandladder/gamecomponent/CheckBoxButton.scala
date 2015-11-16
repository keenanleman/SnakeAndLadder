package snakeandladder.gamecomponent

import java.awt.geom.{Rectangle2D, Point2D, Ellipse2D}
import java.awt.{Font, Color, Graphics2D}
import java.awt.event.MouseEvent

import snakeandladder.engine.ButtonSettings
import snakeandladder.utility.AssetManager

class CheckBoxButton(initialX : Double, initialY : Double, title : String, width : Double,height : Double)
  extends ButtonComponent(initialX, initialY, title, width,height){

  private val buttonSize : Double = ButtonSettings.CHECKBOX_BUTTON_SIZE

  private val buttonShadowSize : Double = ButtonSettings.CHECKBOX_BUTTON_SIZE + 4

  private val checkboxTitleColor : Color = AssetManager.getColor("CheckboxTitleColor")

  private val radioButtonCheckedColor : Color = AssetManager.getColor("CheckboxCheckedColor")

  private val radioButtonUnCheckedColor : Color = AssetManager.getColor("CheckboxUnCheckedColor")

  buttonDrawable = new Rectangle2D.Double(x,y,buttonSize,buttonSize)

  buttonShadowDrawable = new Rectangle2D.Double(x - 2, y - 2, buttonShadowSize, buttonShadowSize)

  private var buttonCheckMarkDrawable : Rectangle2D.Double =
    new Rectangle2D.Double(x, y, buttonSize,buttonSize)

  private var checked : Boolean = false

  def setValue(value : Boolean) : Unit = {
    checked = value
  }
  def getValue : Boolean = checked

  /**
   * Merender title dari tombol
   * @param g2d
   */
  override protected def drawButtonTitle(g2d: Graphics2D): Unit = {
    g2d.setColor(checkboxTitleColor)
    g2d.setFont(buttonFont.deriveFont(Font.PLAIN,15))
    g2d.drawString(title, x.toFloat + 24 , y.toFloat + 28 / 2)
  }

  /**
   * Meng-apply efek saat tombol di tekan
   * @param event
   */
  override protected def buttonClickedEffect(event: MouseEvent): Unit = {
    checked = !checked
  }

  /**
   * Merender tombol
   * @param g2d
   */
  override def drawButton(g2d : Graphics2D) : Unit = {
    if(checked){
      g2d.setColor(radioButtonCheckedColor)
    }else{
      g2d.setColor(radioButtonUnCheckedColor)
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
