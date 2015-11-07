package snakeandladder.gameobject

import java.awt.Graphics
/**
 * Semua benda/objek didalam game merupakan tururan dari kelas ini,
 * semua objek di dalam game harus bisa dirender dan mempunyai posisi awal
 * @param initialX posisi awal x
 * @param initialY posisi awal y
 */
abstract class GameObject(initialX : Double ,initialY : Double) {
  /**
   * Posisi awal x dari objek
   */
  protected var x : Double = initialX
  /**
   * Posisi awal y dari objek
   */
  protected var y : Double = initialY

  /**
   * getter dari variabel x, sebagai posisi awal x dari objek
   * @return posisi awal x
   */
  def getX : Double = x
  /**
   * getter dari variabel y, sebagai posisi awal y dari objek
   * @return posisi awal y
   */
  def getY : Double = y
  /**
   * Setiap objek/benda didalam game memiliki method render yang digunakan untuk
   * merender dirinya sendiri
   * @param graphics graphics dari canvas pada kelas GameDisplay
   */
  def render(graphics : Graphics)
}
