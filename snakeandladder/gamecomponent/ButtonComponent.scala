package snakeandladder.gamecomponent

import java.awt._
import java.awt.geom.{Point2D, RectangularShape}
import java.io.File
import java.awt.event.MouseEvent

import snakeandladder.gameevent.MouseEventListener
import snakeandladder.gameobject.{GameObjectUpdate, GameObject}
import snakeandladder.utility.AssetManager

/**
 * Kelas abstrak dari tombol yand diturunkan menjadi checkbox dan button
 * @param initialX posisi X
 * @param initialY posisi Y
 * @param title judul/text dari tombol
 * @param width lebar dari tombol
 * @param height tinggi dari tombol
 */
abstract class ButtonComponent(initialX : Double, initialY : Double, title : String, width : Double, height : Double)
extends GameObject(initialX, initialY) with MouseEventListener{

  /**
   * Sementara jika assetManager sudah di buat akan dipindahkan
   * Font dari button
   */
  protected var buttonFont : Font = AssetManager.getFont("DefaultFont")

  /**
   * Lebar dari judul tombol
   */
  protected var buttonTitleWidth : Double = AssetManager.getFontMetrics("DefaultFont").stringWidth(title)

  /**
   * Warna foreground dari tombol
   */
  protected var buttonFaceColor : Color = AssetManager.getColor("ButtonFaceColor")
  /**
   * Warna bayangan saat di hover
   */
  protected var secondaryShadowColor : Color = AssetManager.getColor("SecondaryShadowColor")

  /**
   * Warna bayangan hitam
   */
  protected var primaryShadowColor : Color = AssetManager.getColor("PrimaryShadowColor")

  /**
   * Objek yang menjadi tombol
   */
  protected var buttonDrawable : RectangularShape = _

  /**
   * Objek yang akan dirender sebagai bayangan tombol
   */
  protected var buttonShadowDrawable : RectangularShape = _

  /**
   * Warna dari bayangan tombol
   */
  protected var buttonShadowColor : Color = primaryShadowColor

  /**
   * Implementasi kosong dari ButtonAction, jika tombol ingin mempunyai aksi,
   * maka harus di set dengan ButtonAction lain
   */
  protected var buttonAction : ButtonAction = _

  /**
   * Mengeset aksi saat tombol di tekan
   * @param buttonAction
   */
  def setAction(buttonAction : ButtonAction): Unit ={
    this.buttonAction = buttonAction
  }

  /**
   * Merender tombol
   * @param g2d
   */
  protected def drawButton(g2d : Graphics2D) : Unit = {
    g2d.setColor(buttonFaceColor)
    g2d.fill(buttonDrawable)
  }

  /**
   * Merender bayangan dari tombol
   * @param g2d
   */
  protected def drawButtonShadow(g2d : Graphics2D): Unit ={
    g2d.setColor(buttonShadowColor)
    g2d.fill(buttonShadowDrawable)
  }


  /**
   * Merender title dari tombol
   * @param g2d
   */
  protected def drawButtonTitle(g2d : Graphics2D) : Unit

  /**
   * Mengapply efek saat mouse hovering
   */
  protected def mouseHoverEffect : Unit = {
    buttonShadowColor = secondaryShadowColor
  }

  /**
   * Meng-apply efek saat mouse tidak hovering
   */
  protected def mouseExitEffect : Unit = {
    buttonShadowColor = primaryShadowColor
  }

  /**
   * Meng-apply efek saat tombol di tekan
   * @param event
   */
  protected def buttonClickedEffect(event : MouseEvent) : Unit

  /**
   * Meng-apply efek saat tombol di lepas
   */
  protected def buttonReleaseEffect : Unit

  /**
   * Setiap objek/benda didalam game memiliki method render yang digunakan untuk
   * merender dirinya sendiri
   * @param graphics graphics dari canvas pada kelas GameDisplay
   */
  override def render(graphics: Graphics): Unit = {
    var g2d : Graphics2D = graphics.asInstanceOf[Graphics2D]
    drawButtonShadow(g2d)
    drawButton(g2d)
    drawButtonTitle(g2d)
  }

  /**
   * Method yang akan dipanggil ketika event mouse terjadi
   * @param event event yang terjadi
   */
  override def triggeredMouseEvent(event: MouseEvent): Unit = {
    var curPos : Point2D = event.getPoint
    if(buttonDrawable.contains(new Point2D.Double(event.getX,event.getY))){
      if(MouseEvent.getMouseModifiersText(event.getModifiersEx).equals("Button1")){
        buttonClickedEffect(event)
        buttonAction.buttonClicked
      }else{
        mouseHoverEffect
      }
    }else{
      mouseExitEffect
    }
  }

}
