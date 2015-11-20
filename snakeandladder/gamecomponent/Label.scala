package snakeandladder.gamecomponent

import java.awt.{Graphics2D, Font, Graphics}
import java.awt.geom.RoundRectangle2D

import snakeandladder.engine.LabelSettings
import snakeandladder.gameobject.GameObject
import snakeandladder.utility.AssetManager

/**
 * Komponen yang digunakan untuk menampilkan text pada state
 * @param initialX posisi x
 * @param initialY posisi y
 * @param title judul/text pada label
 */
class Label(initialX : Double, initialY : Double, title : String) 
extends GameObject(initialX, initialY) with DataBinder {
  /**
   * Font dari label
   */
  protected var labelFont : Font = AssetManager.getFont("DefaultFont")

  /**
   * Judul/text dari label
   */
  protected var titleText = title

  /**
   * Warna dari label
   */
  protected var labelColor = AssetManager.getColor("LabelColor")

  /**
   * Warna dari judul/text label
   */
  protected var labelTitleColor = AssetManager.getColor("LabelTitleColor")

  /**
   * Lebar dari judul label
   */
  protected var labelFontWidth : Double = AssetManager.getFontMetrics("DefaultFont").stringWidth(titleText)

  /**
   * Objek yang digambar sebagai label
   */
  protected var labelDrawable = new RoundRectangle2D.Double(x,y,labelFontWidth + 16,LabelSettings.LABEL_HEIGHT,5,5)

  /**
   * Mengembalikan text dalam label
   * @return judul label
   */
  def getText : String = titleText

  /**
   * Mengeset tulisan/text pada label
   * @param titleText text label
   */
  def setText(titleText : String) : Unit = {
    this.titleText = titleText
    labelFontWidth = AssetManager.getFontMetrics("DefaultFont").stringWidth(titleText)
    labelDrawable.width = labelFontWidth + 16
  }
  /**
    * Setiap objek/benda didalam game memiliki method render yang digunakan untuk
    * merender dirinya sendiri
    * @param graphics graphics dari canvas pada kelas GameDisplay
    */
  override def render(graphics: Graphics): Unit = {
    graphics.setColor(labelColor)
    graphics.asInstanceOf[Graphics2D].fill(labelDrawable)
    graphics.setColor(labelTitleColor)
    graphics.setFont(labelFont)
    graphics.asInstanceOf[Graphics2D].drawString(titleText,
      (labelDrawable.x + (labelDrawable.getWidth / 2) - (labelFontWidth / 2)).toFloat,
      (labelDrawable.y + (labelDrawable.getHeight/2) + 4).toFloat
    )
  }
}
