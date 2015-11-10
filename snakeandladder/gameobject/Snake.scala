package snakeandladder.gameobject

import java.awt._
import java.awt.geom.{Ellipse2D, Point2D, Path2D}
import java.util.Random

import com.sun.javafx.geom.RoundRectangle2D
import snakeandladder.utility.{PointToPointIterator}

/**
 * Representasi dari ular pada board
 * @param from tile tempat kepala ular
 * @param to tile tempat ekor ular
 */
class Snake(from : Tile, to : Tile) extends GameObject(from.getX,from.getY) {

  /**
   * Koordinat x tempat ekor ular
   */
  private val x1 : Double = to.getX
  /**
   * Koordinat y tempat ekor ular
   */
  private val y1 : Double = to.getY

  /**
   * Path/drawable yang menjadi badan ular
   */
  private val snake : Path2D.Double = new Path2D.Double()
  /**
   * Shape/drawable yang menjadi kepala ular
   */
  private var snakeHead : Shape = null
  /**
   * Pattern osilasi x
   */
  private var xOsilationPattern : Double = 0
  /**
   * Pattern osilasi y
   */
  private var yOsilationPattern : Double = 0
  /**
   * titik tengan dari tile
   */
  private var centerTilePoint : Double = Tile.TILE_SIZE/2

  /* Memanggil method untuk membuat snake */
  buildSnake

  /**
   * Menentukan arah/pattern osilasi badan ular
   */
  private def determinedOsilationPattern : Unit = {
    if(from.getX > to.getX){
      xOsilationPattern = 1
      yOsilationPattern = 1
    }else if(from.getX < to.getX){
      xOsilationPattern = -1
      yOsilationPattern = 1
    }else{
      xOsilationPattern = 1
      yOsilationPattern = 0
    }
  }

  /**
   * Method untuk mempersiapkan drawable untuk snake
   */
  private def buildSnake : Unit = {
    determinedOsilationPattern

    var iterator : PointToPointIterator  = new PointToPointIterator(new Point2D.Double(x + centerTilePoint ,y + centerTilePoint),
      new Point2D.Double(x1+ centerTilePoint,y1+ centerTilePoint),
      Snake.CURVE_GAP)

    var currentX = iterator.getCurrentX
    var currentY = iterator.getCurrentY
    iterator.next
    var nextX = iterator.getCurrentX
    var nextY = iterator.getCurrentY

    var negator : Int = -1
    snake.moveTo(currentX, currentY)
    while (iterator.hasNext) {
      if(!iterator.isLastIteration) {
        snake.curveTo(currentX + (Snake.BEZIER_POWER * iterator.getYStep * negator * xOsilationPattern),
          currentY + (Snake.BEZIER_POWER * iterator.getXStep * negator * yOsilationPattern),
          nextX + (Snake.BEZIER_POWER * iterator.getYStep * negator * xOsilationPattern),
          nextY + (Snake.BEZIER_POWER * iterator.getXStep * negator * yOsilationPattern),
          nextX,
          nextY)
      }else{
        snake.curveTo(currentX + (Snake.BEZIER_POWER * iterator.getYStep * negator * xOsilationPattern),
          currentY + (Snake.BEZIER_POWER * iterator.getXStep * negator * yOsilationPattern),
          nextX + (Snake.BEZIER_POWER * iterator.getYStep * negator * xOsilationPattern),
          nextY + (Snake.BEZIER_POWER * iterator.getXStep * negator * yOsilationPattern),
          x1 + centerTilePoint,
          y1 + centerTilePoint)
      }
      currentX = iterator.getCurrentX
      currentY = iterator.getCurrentY
      iterator.next
      nextX = iterator.getCurrentX
      nextY = iterator.getCurrentY
      negator *= -1
    }
    snakeHead = new Ellipse2D.Double(x + centerTilePoint - Snake.SNAKE_HEAD_WIDTH / 2,
      y + centerTilePoint - Snake.SNAKE_HEAD_HEIGHT / 2,
      Snake.SNAKE_HEAD_WIDTH,
      Snake.SNAKE_HEAD_HEIGHT)
  }

  /**
   * Digunakan untuk merender drawable dari snake
   * @param graphics graphics dari canvas pada kelas GameDisplay
   */
  override def render(graphics : Graphics) : Unit = {
    var g2d : Graphics2D = graphics.asInstanceOf[Graphics2D]
    g2d.setStroke(Snake.SNAKE_STROKE)
    g2d.fill(snakeHead)
    g2d.draw(snake)
  }
}

object Snake{

  /**
   * Panjang gelombang ular / lamda
   */
  val random = new Random(System.nanoTime())
  val CURVE_GAP : Int = random.nextInt(100) + 29
  /**
   * Bilangan peng-kali untuk  jarak terdekat titik-titik bezier dari titik seimbang gelombang
   */
  val BEZIER_POWER : Int = 1
  /**
   * Lebar dari garis pembentuk badan ular
   */
  val SNAKE_STROKE : Stroke = new BasicStroke(8)
  /**
   * Lebar dari kepala ular
   */
  val SNAKE_HEAD_WIDTH : Double = 25
  /**
   * Tinggi dari kepala ular
   */
  val SNAKE_HEAD_HEIGHT : Double = 15
}
