package snakeandladder.utility

import java.awt.{Graphics2D, Graphics}
import java.awt.geom._

/**
 * Iterator untuk sebuah path2D atau garis yang mempunyai bezier curve
 * @param line
 */
class DynamicLineIterator (line : Path2D){
  /**
   * Iterator untuk Path2D
   */
  private val iterator : PathIterator = line.getPathIterator(null)
  /**
   * Posisi x
   */
  private var curX : Double = 0
  /**
   * Posisi y
   */
  private var curY : Double = 0
  /**
   * Posisi waktu t (0,1 - 0.9)
   */
  private var t: Double = 0.1
  /**
   * Segment dari sebuah path2D
   */
  private var segPoint : Array[Double] = new Array[Double](6)

  /**
   * Mengembalikan true jika masih ada titik selanjutnya pada path
   * @return true jika masih ada titik selanjutnya dan sebaliknya
   */
  def hasNext : Boolean = iterator.isDone

  /**
   * Pindah ke titik selanjutnya
   */
  def next : Unit = {
    if(iterator.isDone) return
    if(t >= 1){
      iterator.next
      t = 0.1
    }
    if(iterator.currentSegment(segPoint) == PathIterator.SEG_CUBICTO) {
      val bcp30: Double = bernsteinPolynomialConstant(3, 0, t)
      val bcp31: Double = bernsteinPolynomialConstant(3, 1, t)
      val bcp32: Double = bernsteinPolynomialConstant(3, 2, t)
      val bcp33: Double = bernsteinPolynomialConstant(3, 3, t)
      val p0 = new Point2D.Double(curX * bcp30, curY * bcp30)
      val p1 = new Point2D.Double(segPoint(0) * bcp31, segPoint(1) * bcp31)
      val p2 = new Point2D.Double(segPoint(2) * bcp32, segPoint(3) * bcp32)
      val p3 = new Point2D.Double(segPoint(4) * bcp33, segPoint(5) * bcp33)
      curX = p0.x + p1.x + p2.x + p3.x
      curY = p0.y + p1.y + p2.y + p3.y
      t += 0.1
    }else{
      curX = segPoint(0)
      curY = segPoint(1)
      iterator.next
    }
  }

  /**
   * Mengembalikan titik yang sekarang di tunjuk iterator
   * @return
   */
  def getPoint : Point2D = {
    return new Point2D.Double(curX,curY)
  }

  /**
   * Menghitung bernstein polynomial constant
   * @param n jumlah orde
   * @param m orde yang di cari
   * @param t waktu(0.1 - 0.9)
   * @return
   */
  private def bernsteinPolynomialConstant(n : Double, m : Double,t : Double): Double = {
    return combination(n,m) * math.pow(t,m) * math.pow(1 - t,n-m)
  }

  /**
   * Menghitung kombinasi
   * @param n jumlah objek kombinasi
   * @param m jumlah objek yang di ambil
   * @return
   */
  private def combination(n : Double, m : Double) : Double = {
    return factorial(n) / (factorial(m) * factorial(n - m))
  }

  /**
    * Fungsi factorial untuk bilangan kecil
    * @param n bilangan yang akan di factorial
    * @return hasil factorial
    */
  private def factorial(n : Double): Double ={
    if(n <= 1) return 1
    return n * factorial(n - 1)
  }

}
