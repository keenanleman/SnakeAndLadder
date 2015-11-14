package snakeandladder.gamecomponent

import java.awt._
import java.awt.event.{MouseEvent}
import java.awt.geom.{Point2D, RoundRectangle2D}
import java.io.File

import snakeandladder.engine.{ButtonSettings}
import snakeandladder.gameevent.{MouseEventListener}
import snakeandladder.gameobject.{GameObjectUpdate, GameObject}

/**
 * Kelas yang merepresentasikan tombol
 * @param initialX posisi awal x
 * @param initialY posisi awal y
 * @param title title dari tombol
 * @param width lebar dari tombol
 */
class Button(initialX : Double, initialY : Double, title : String, width : Double) extends GameObject(initialX, initialY)
with MouseEventListener with GameObjectUpdate{

  /**
   * Warna bayangan biru
   */
  private var blueShadow : Color = new Color(Color.blue.getRed,Color.blue.getGreen,Color.blue.getBlue,100)

  /**
   * Warna bayangan hitam
   */
  private var blackShadow : Color = new Color(Color.black.getRed,Color.black.getGreen,Color.black.getBlue,50)


  /**
   * Objek yang menjadi tombol
   */
  private var buttonDrawable : RoundRectangle2D.Double =
    new RoundRectangle2D.Double(x,y,width,ButtonSettings.BUTTON_HEIGHT,5,5)

  /**
   * Objek yang akan dirender sebagai bayangan tombol
   */
  private var buttonShadowDrawable = new RoundRectangle2D.Double(x - 2,y - 2,width + 4,ButtonSettings.BUTTON_HEIGHT + 4,5,5)

  /**
   * Warna dari bayangan tombol
   */
  private var buttonShadowColor : Color = blackShadow

  /**
   * Variable untuk mendelay efek saat tombol di tekan
   */
  private var buttonBounceBackDelay : Int = 0

  /**
   * Sementara jika assetManager sudah di buat akan dipindahkan
   * Font dari button
   */
  var buttonFont = Font.createFont(Font.TRUETYPE_FONT,new File("/home/keenan/IdeaProjects/SnakeAndLadder/src/snakeandladder/JosefinSans-SemiBold.ttf"))

  /**
   * Implementasi kosong dari ButtonAction, jika tombol ingin mempunyai aksi,
   * maka harus di set dengan ButtonAction lain
   */
  private var buttonAction : ButtonAction = new ButtonAction {
    override def buttonClicked: Unit = {}
  }

  /**
   * Mengeset aksi saat tombol di tekan
   * @param buttonAction
   */
  def setAction(buttonAction : ButtonAction): Unit ={
    this.buttonAction = buttonAction
  }

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
   * Merender tombol
   * @param g2d
   */
  private def drawButton(g2d : Graphics2D) : Unit = {
    g2d.setColor(Color.WHITE)
    g2d.fill(buttonDrawable)
  }

  /**
   * Merender bayangan dari tombol
   * @param g2d
   */
  private def drawButtonShadow(g2d : Graphics2D): Unit ={
    g2d.setColor(buttonShadowColor)
    g2d.fill(buttonShadowDrawable)
  }


  /**
   * Merender title dari tombol
   * @param g2d
   */
  private def drawButtonTitle(g2d : Graphics2D) : Unit = {
    g2d.setColor(Color.BLACK)
    g2d.setFont(buttonFont.deriveFont(Font.PLAIN,15))
    g2d.drawString(title, buttonDrawable.x.toFloat + width.toFloat / 8, buttonDrawable.y.toFloat + 5 + ButtonSettings.BUTTON_HEIGHT.toFloat / 2)
  }


  /**
   * Event listener saat terjadi event mouse
   * @param event
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

  /**
   * Mengapply efek saat mouse hovering
   */
  private def mouseHoverEffect : Unit = {
    buttonShadowColor = blueShadow
  }

  /**
   * Meng-apply efek saat mouse tidak hovering
   */
  private def mouseExitEffect : Unit = {
    buttonShadowColor = blackShadow
  }

  /**
   * Meng-apply efek saat tombol di tekan
   * @param event
   */
  private def buttonClickedEffect(event : MouseEvent) : Unit = {
    buttonBounceBackDelay = 7

    buttonDrawable.width = width - 4
    buttonDrawable.height = ButtonSettings.BUTTON_HEIGHT - 4
    buttonDrawable.x = x + 2
    buttonDrawable.y = y + 2

    buttonShadowDrawable.width = width
    buttonShadowDrawable.height = ButtonSettings.BUTTON_HEIGHT
    buttonShadowDrawable.x = x
    buttonShadowDrawable.y = y
  }

  /**
   * Meng-apply efek saat tombol di lepas
   */
  private def buttonReleaseEffect : Unit = {
    buttonDrawable.width = width
    buttonDrawable.height = ButtonSettings.BUTTON_HEIGHT
    buttonDrawable.x = x
    buttonDrawable.y = y

    buttonShadowDrawable.width = width + 4
    buttonShadowDrawable.height = ButtonSettings.BUTTON_HEIGHT + 4
    buttonShadowDrawable.x = x - 2
    buttonShadowDrawable.y = y - 2
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

