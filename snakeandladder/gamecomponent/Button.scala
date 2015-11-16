package snakeandladder.gamecomponent

import java.awt._
import java.awt.event.{MouseEvent}
import java.awt.geom.{Point2D, RoundRectangle2D}

import snakeandladder.engine.{ButtonSettings}
import snakeandladder.gameevent.{MouseEventListener}
import snakeandladder.gameobject.{GameObjectUpdate, GameObject}
import snakeandladder.utility.AssetManager

/**
 * Kelas yang merepresentasikan tombol
 * @param initialX posisi awal x
 * @param initialY posisi awal y
 * @param title title dari tombol
 * @param width lebar dari tombol
 */
class Button(initialX : Double, initialY : Double, title : String, width : Double,height : Double)
  extends ButtonComponent(initialX : Double, initialY : Double, title : String, width : Double, height : Double)
  with GameObjectUpdate{

  /**
   * Objek yang menjadi tombol
   */
  buttonDrawable = new RoundRectangle2D.Double(x,y,width,height,5,5)

  /**
   * Objek yang akan dirender sebagai bayangan tombol
   */
  buttonShadowDrawable = new RoundRectangle2D.Double(x - 2,y - 2,width + 4,height + 4,5,5)

  /**
   * Variable untuk mendelay efek saat tombol di tekan
   */
  private var buttonBounceBackDelay : Int = 0

  /**
   * Merender title dari tombol
   * @param g2d
   */
  override def drawButtonTitle(g2d : Graphics2D) : Unit = {
    g2d.setColor(AssetManager.getColor("ButtonTitleColor"))
    g2d.setFont(buttonFont.deriveFont(Font.PLAIN,15))
    g2d.drawString(title,
      buttonDrawable.asInstanceOf[RoundRectangle2D.Double].x.toFloat + (width.toFloat / 2) - (buttonTitleWidth / 2).toFloat,
      buttonDrawable.asInstanceOf[RoundRectangle2D.Double].y.toFloat + 5 + ButtonSettings.BUTTON_HEIGHT.toFloat / 2
    )
  }


  /**
   * Meng-apply efek saat tombol di tekan
   * @param event
   */
  override def buttonClickedEffect(event : MouseEvent) : Unit = {
    buttonBounceBackDelay = 7

    var roundButton : RoundRectangle2D.Double = buttonDrawable.asInstanceOf[RoundRectangle2D.Double]
    var roundButtonShadow : RoundRectangle2D.Double = buttonShadowDrawable.asInstanceOf[RoundRectangle2D.Double]

    roundButton.width = width - 4
    roundButton.height = ButtonSettings.BUTTON_HEIGHT - 4
    roundButton.x = x + 2
    roundButton.y = y + 2

    roundButtonShadow.width = width
    roundButtonShadow.height = ButtonSettings.BUTTON_HEIGHT
    roundButtonShadow.x = x
    roundButtonShadow.y = y
  }

  /**
   * Meng-apply efek saat tombol di lepas
   */
  override def buttonReleaseEffect : Unit = {
    var roundButton : RoundRectangle2D.Double = buttonDrawable.asInstanceOf[RoundRectangle2D.Double]

    var roundButtonShadow : RoundRectangle2D.Double = buttonShadowDrawable.asInstanceOf[RoundRectangle2D.Double]

    roundButton.width = width
    roundButton.height = ButtonSettings.BUTTON_HEIGHT
    roundButton.x = x
    roundButton.y = y

    roundButtonShadow.width = width + 4
    roundButtonShadow.height = ButtonSettings.BUTTON_HEIGHT + 4
    roundButtonShadow.x = x - 2
    roundButtonShadow.y = y - 2
  }
  /**
   * Method update yang harus di implementasikan semua GameObject yang mampu mengupdate
   * data
   */
  override def update(): Unit = {
    if(buttonBounceBackDelay > 1){
      buttonBounceBackDelay -= 1
    }else{
      buttonReleaseEffect
      buttonBounceBackDelay -= 1
    }
  }

}

