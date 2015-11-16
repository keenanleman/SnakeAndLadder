package snakeandladder.gamecomponent

import java.awt.{Graphics2D, Font, Graphics}
import java.awt.geom.RoundRectangle2D

import snakeandladder.engine.LabelSettings
import snakeandladder.gameobject.GameObject
import snakeandladder.utility.AssetManager

class Label(initialX : Double, initialY : Double, title : String) extends GameObject(initialX, initialY) {
  protected var labelFont : Font = AssetManager.getFont("DefaultFont")

  protected var titleText = title

  protected var labelColor = AssetManager.getColor("LabelColor")

  protected var labelTitleColor = AssetManager.getColor("LabelTitleColor")

  protected var labelFontWidth : Double = AssetManager.getFontMetrics("DefaultFont").stringWidth(titleText)

  protected var labelDrawable = new RoundRectangle2D.Double(x,y,labelFontWidth + 16,LabelSettings.LABEL_HEIGHT,5,5)

  def getText : String = titleText

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
