package snakeandladder.gamecomponent

import java.awt.{TexturePaint, Graphics2D, Graphics}
import java.awt.geom.Rectangle2D
import java.awt.image.BufferedImage

import snakeandladder.engine.GameDisplaySettings
import snakeandladder.gameobject.GameObject

/**
 * Kelas sebagai background dari state dalam game
 */
class Background(backgroundImage: BufferedImage) extends GameObject(0,0){
  /**
   * Objek yang dirender sebagai background
   */
  private var background : Rectangle2D.Double =
    new Rectangle2D.Double(x,y,GameDisplaySettings.WINDOW_WIDTH,GameDisplaySettings.WINDOW_HEIGHT)

  /**
   * Paint/Gambar yang digunakan sebagai gambar yang digunakan sebagai background
   */
  private var texturePaint : TexturePaint = new TexturePaint(backgroundImage,background)

  /**
    * Setiap objek/benda didalam game memiliki method render yang digunakan untuk
    * merender dirinya sendiri
    * @param graphics graphics dari canvas pada kelas GameDisplay
    */
  override def render(graphics: Graphics): Unit = {
    graphics.asInstanceOf[Graphics2D].setPaint(texturePaint)
    graphics.asInstanceOf[Graphics2D].fill(background)
  }
}
