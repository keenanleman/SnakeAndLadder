package snakeandladder.gameobject

import java.awt._
import java.awt.geom.Rectangle2D

/**
 * Representasi dari tile pada board
 * @param initialX posisi awal x
 * @param initialY posisi awal y
 * @param tileNumber nomor yang akan ditampilkan pada tile
 * @param color warna tile
 */
class Tile(initialX : Double, initialY : Double, tileNumber : Int, color : Color) extends GameObject(initialX,initialY){
  /**
   * Objek Rengtangle yang akan menjadi tile
   */
  private val rectangle : Rectangle2D = new Rectangle2D.Double(x, y,Tile.TILE_SIZE,Tile.TILE_SIZE )
  /**
   * Posisi x nomor tile
   */
  private val titleX : Float = (Tile.TILE_SIZE - 25 + x).asInstanceOf[Float]
  /**
   * Posisi y nomor tile
   */
  private val titleY : Float = (Tile.TILE_SIZE - 5 + y).asInstanceOf[Float]

  /**
    * Status tile mengenai kepemilikan snake
    */
  private var hasSnake : Boolean = false

  /**
   * Mengembalikan nomor tile
   * @return nomor tile
   */
  def getTileNumber : Int = tileNumber

  /**
   * Mengembalikan status kepemilikan ular
   * @return
   */
  def isHasSnake : Boolean = {
    return hasSnake
  }

  /**
   * Menandai status kepemilikan ular
   */
  def gotSnake : Unit = {
    hasSnake = true
  }

  /**
   * Merender tile
   * @param graphics graphics dari canvas pada kelas GameDisplay
   */
  override def render(graphics: Graphics) : Unit ={
    var graphics2d : Graphics2D = graphics.asInstanceOf[Graphics2D]
    graphics2d.setColor(color)
    graphics2d.fill(rectangle)
    graphics2d.setColor(Color.BLACK)
    graphics2d.drawString(tileNumber.toString,titleX,titleY)
  }
}

object Tile{
  /**
   * Ukuran tile
   */
  val TILE_SIZE : Int = 64
  /**
   * Jumlah warna tile
   */
  val TILE_COLOR_NUM = 4
  /**
   * Warna tile bernomor ganjil
   */
  val TILE_COLOR_ODD = Color.GRAY
  /**
   * Warna tile bernomor genap
   */
  val TILE_COLOR_EVEN = Color.WHITE
}