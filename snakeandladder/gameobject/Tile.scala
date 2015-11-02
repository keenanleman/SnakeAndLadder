package snakeandladder.gameobject

import java.awt._

import scala.util.Random

/**
 * Representasi dari tile pada board
 * @param initialX posisi awal x
 * @param initialY posisi awal y
 * @param tileNumber nomor yang akan ditampilkan pada tile
 * @param color warna tile
 */
class Tile(initialX : Int, initialY : Int, tileNumber : Int, color : Color) extends GameObject(initialX,initialY){
  /**
   * Objek Rengtangle yang akan menjadi tile
   */
  private val rectangle : Rectangle = new Rectangle(x, y,Tile.TILE_SIZE,Tile.TILE_SIZE )
  /**
   * Posisi x nomor tile
   */
  private val titleX : Float = ((Tile.TILE_SIZE/2.0) + x).asInstanceOf[Float]
  /**
   * Posisi y nomor tile
   */
  private val titleY : Float = ((Tile.TILE_SIZE/2.0) + y).asInstanceOf[Float]

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
  private val random : Random = new Random(System.nanoTime());
  /**
   * Ukuran tile
   */
  val TILE_SIZE : Int = 64
  /**
   * Jumlah warna tile
   */
  val TILE_COLOR_NUM = 4
  /**
   * Ketebalan garis
   */
  val TILE_BORDER_THICKNESS = 2
  /**
   * Warna tile
   */
  val TILE_COLORS : Array[Color] = Array(
    Color.BLUE,
    Color.GREEN,
    Color.GRAY,
    Color.RED
  )

  /**
   * Merandom warna tile
   * @return
   */
  def getTileColor : Color = Tile.TILE_COLORS(Tile.random.nextInt(Tile.TILE_COLOR_NUM))
}