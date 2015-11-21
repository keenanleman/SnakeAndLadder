package snakeandladder.gameobject

import java.awt._
import java.awt.geom._
import javax.sound.sampled.Line

import snakeandladder.engine.{LadderSettings, SnakeSettings, BoardSettings}
import snakeandladder.utility.{AssetManager, DynamicLineIterator, PointToPointIterator}

/**
  * Representasi dari tangga pada board
  * @param from tile bawah tangga
  * @param to tile atas tangga
  */
class Ladder(from : Tile, to : Tile) extends GameObject(from.getX,from.getY) {

  /**
    * Koordinat x tempat bawah tangga
    */
  private var x1 : Double = to.getX
  /**
    * Koordinat y tempat bawah tangga
    */
  private var y1 : Double = to.getY

  /**
   * Titik kiri tile
   */
  private var leftTilePoint : Double = BoardSettings.TILE_SIZE / 2

  /**
   * Objek yang dirender sebagai tiang kiri dari tangga
   */
  private var leftDrawable : Path2D.Double = new Path2D.Double
  /**
   * Objek yang dirender sebagai tiang kanan dari tangga
   */
  private var rightDrawable: Path2D.Double = new Path2D.Double
  /**
    * Pattern osilasi x
    */
  private var xOsilationPattern : Double = 0
  /**
    * Pattern osilasi y
    */
  private var yOsilationPattern : Double = 0

  /* Memanggil method untuk membuat ladder */
  buildLadder

  /**
    * Menentukan arah/pattern osilasi anak tangga
    */
  private def determinedOsilationPattern : Unit = {
    if(from.getX > to.getX){
      xOsilationPattern = -1
      yOsilationPattern = 1
    }else if(from.getX < to.getX){
      xOsilationPattern = 1
      yOsilationPattern = 1
    }else{
      xOsilationPattern = 1
      yOsilationPattern = 0
    }
  }

  /**
    * Method untuk mempersiapkan drawable untuk snake
    */
  private def buildLadder : Unit = {
    determinedOsilationPattern
    var iterator : PointToPointIterator  = new PointToPointIterator(new Point2D.Double(x + (leftTilePoint),
      y + (leftTilePoint)),
      new Point2D.Double(x1+ (leftTilePoint) ,
        y1+ (leftTilePoint)),
      LadderSettings.LADDER_GAP)

    var currentX = iterator.getCurrentX
    var currentY = iterator.getCurrentY
    iterator.next
    var nextX = iterator.getCurrentX
    var nextY = iterator.getCurrentY

    leftDrawable.moveTo(currentX, currentY)
    rightDrawable.moveTo(currentX + (iterator.getYStep * xOsilationPattern), currentY + (iterator.getXStep * yOsilationPattern))
    while (iterator.hasNext) {
      leftDrawable.lineTo(nextX, nextY)
      leftDrawable.lineTo(nextX + (iterator.getYStep * xOsilationPattern), nextY + (iterator.getXStep * yOsilationPattern))
      rightDrawable.lineTo(nextX + (iterator.getYStep * xOsilationPattern), nextY + (iterator.getXStep * yOsilationPattern))
      leftDrawable.moveTo(nextX,nextY)
      currentX = iterator.getCurrentX
      currentY = iterator.getCurrentY
      iterator.next
      nextX = iterator.getCurrentX
      nextY = iterator.getCurrentY
    }
    leftDrawable.lineTo(nextX, nextY)
    rightDrawable.lineTo(nextX + (iterator.getYStep * xOsilationPattern), nextY + (iterator.getXStep * yOsilationPattern))
  }

  /**
   * Mengambil objek gambar tangga
   * @return objek gambar tangga
   */
  def getDrawable : Path2D = rightDrawable
  /**
   * Mengambil tile dimana tangga berakhir
   * @return tile akhir tangga
   */
  def getTo : Tile = to
  /**
   * Mengambil tile dimana tangga dimulai
   * @return tile awal tangga
   */
  def getFrom : Tile = from

  /**
    * Digunakan untuk merender drawable dari snake
    * @param graphics graphics dari canvas pada kelas GameDisplay
    */
  override def render(graphics : Graphics) : Unit = {
    var g2d : Graphics2D = graphics.asInstanceOf[Graphics2D]
    g2d.setStroke(SnakeSettings.SNAKE_STROKE)
    g2d.setColor(Color.RED)
    g2d.draw(leftDrawable)
    g2d.draw(rightDrawable)
  }
}
