package snakeandladder.gamecomponent

import java.awt.{Graphics2D, Color, Graphics}
import java.awt.geom.RoundRectangle2D

import snakeandladder.gameobject.GameObject
import snakeandladder.utility.AssetManager

/**
 * Kelas yang menjadi panel pada state
 * @param initialX posisi x
 * @param initialY posisi y
 * @param width lebar panel
 * @param height tinggi panel
 */
class Panel(initialX : Double, initialY : Double, width : Double, height : Double)
  extends GameObject(initialX, initialY){

  /**
   * Objek yang dirender sebagai panel
   */
  var panelPlate = new RoundRectangle2D.Double(x,y,width,height,20,20)
  /**
   * Warna dari panel
   */
  var panelColor = AssetManager.getColor("PanelColor")
  /**
    * Setiap objek/benda didalam game memiliki method render yang digunakan untuk
    * merender dirinya sendiri
    * @param graphics graphics dari canvas pada kelas GameDisplay
    */
  override def render(graphics: Graphics): Unit = {
    graphics.setColor(panelColor)
    graphics.asInstanceOf[Graphics2D].fill(panelPlate)
  }
}
