package snakeandladder.gameobject

import java.awt._
import java.awt.geom.Rectangle2D

import snakeandladder.engine.BoardSettings
import snakeandladder.utility.AssetManager

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
  private val rectangle : Rectangle2D = new Rectangle2D.Double(x, y,BoardSettings.TILE_SIZE,BoardSettings.TILE_SIZE )
  /**
   * Posisi x nomor tile
   */
  private val titleX : Float = (BoardSettings.TILE_SIZE - 25 + x).asInstanceOf[Float]
  /**
   * Posisi y nomor tile
   */
  private val titleY : Float = (BoardSettings.TILE_SIZE - 5 + y).asInstanceOf[Float]

  /**
   * Warna dari judul tile
   */
  private var titleColor : Color = _

  /* Menentukan apakah tile ganjil atau genap */
  if(tileNumber % 2 == 0){
    titleColor = AssetManager.getColor("TileColorOdd")
  }else{
    titleColor = AssetManager.getColor("TileColorEven")
  }

  /**
   * Font dari judul/text tile
   */
  private val titleFont : Font = AssetManager.getFont("DefaultFont")

  /**
    * Status tile mengenai kepemilikan snake
    */
  private var hasSnake : Boolean = false

  /**
    * Status tile mengenai kepemilikan ular
    */
  private var hasLadder : Boolean = false

  /**
    * Kepala ular yang dimiliki tile
    */
  private var snakeHead : Snake = null

  /**
    * Tangga yang dimiliki tile
    */
  private var ladder : Ladder = null

  /**
   * Mengeset ular pada tile
   * @param snakeHead ular yang di-set kepada tile
   */
  def setSnakeHead(snakeHead : Snake): Unit ={
    this.snakeHead = snakeHead
  }
  /**
   * Mengembalikan ular yang dimiliki tile
   * @return ular yang dimiliki tile
   */
  def getSnakeHead : Snake = snakeHead

  /**
   * Mengeset tangga pada tile
   * @param ladder tangga yang di-set kepada tile
   */
  def setLadder(ladder : Ladder): Unit ={
    this.ladder = ladder
  }

  /**
   * Mengembalikan tanga yang dimiliki tile
   * @return tangga yang dimiliki tile
   */
  def getLadder : Ladder = ladder

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
   * Mengembalikan status kepemilikan tangga
   * @return
   */
  def isHasLadder : Boolean = hasLadder

  /**
   * Menandai status kepemilikan tangga
   */
  def gotLadder : Unit = {
    hasLadder = true
  }

  /**
   * Merender tile
   * @param graphics graphics dari canvas pada kelas GameDisplay
   */
  override def render(graphics: Graphics) : Unit ={
    var graphics2d : Graphics2D = graphics.asInstanceOf[Graphics2D]
    graphics2d.setColor(color)
    graphics2d.fill(rectangle)
    graphics2d.setColor(titleColor)
    graphics2d.setFont(titleFont)
    graphics2d.drawString(tileNumber.toString,titleX,titleY)
  }
}

