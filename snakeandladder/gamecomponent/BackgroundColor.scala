package snakeandladder.gamecomponent

import java.awt.geom.Rectangle2D
import java.awt.{Color, Graphics2D, Graphics}

import snakeandladder.engine.GameDisplaySettings
import snakeandladder.gameobject.GameObject

class BackgroundColor(backgroundBlur: Color) extends GameObject(0,0){
  private var background : Rectangle2D.Double =
    new Rectangle2D.Double(x,y,GameDisplaySettings.WINDOW_WIDTH,GameDisplaySettings.WINDOW_HEIGHT)
  /**
    * Setiap objek/benda didalam game memiliki method render yang digunakan untuk
    * merender dirinya sendiri
    * @param graphics graphics dari canvas pada kelas GameDisplay
    */
  override def render(graphics: Graphics): Unit = {
    graphics.asInstanceOf[Graphics2D].setColor(backgroundBlur)
    graphics.asInstanceOf[Graphics2D].fill(background)
  }
}
