package snakeandladder.gameobject

import java.awt.Graphics
/**
 * Semua benda/objek didalam game merupakan tururan dari kelas ini,
 * semua objek di dalam game harus bisa dirender dan mempunyai posisi awal
 * @param initialX posisi awal x
 * @param initialY posisi awal y
 */
abstract class GameObject(initialX : Int,initialY : Int) {
  /**
   * Setiap objek/benda didalam game memiliki method render yang digunakan untuk
   * merender dirinya sendiri
   * @param graphics graphics dari canvas pada kelas GameDisplay
   */
  def render(graphics : Graphics)
}
