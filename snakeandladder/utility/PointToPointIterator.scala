package snakeandladder.utility

import java.awt.geom.Point2D

/**
 * Merupakan iterator yang berfungsi untuk mengiterasi langkah dari suatu titik ke titik lain dalam sebuah garis lurus
 * @param from titik awal
 * @param to titik akhir
 * @param step besar langkah
 */
class PointToPointIterator(from : Point2D, to : Point2D, step : Double){
  /**
   * Nilai absolut jarak antara posisi x titik mula dan titik akhir
   */
  private val xDelta : Double = math.abs(from.getX - to.getX)
  /**
   * Nilai absolut jarak antara posisi y titik mula dan titik akhir
   */
  private val yDelta : Double = math.abs(from.getY - to.getY)
  /**
   * Jarak antara titik mula dan titik akhir
   */
  private val range : Double = math.hypot(xDelta,yDelta)
  /**
   * Jumlah langkah yang dapat di iterasi
   */
  private val numStep : Double = (range / step).ceil + 1

  /**
   * Nilai absolut besar setiap langkah
   */
  private val singleStep = range/numStep
  /**
   * Nilai absolut jarak x sebelum melangkah dan setelah melangkah
   */
  private val singleDeltaX = (xDelta / numStep).ceil
  /**
   * Nilai absolut jarak y sebelum melangkah dan setelah melangkah
   */
  private val singleDeltaY = (yDelta / numStep).ceil

  /**
   * Nilai absolut besar langkah x berdasarkan gradien garis
   */
  private val xStep : Double = math.sqrt(math.abs(math.pow(singleStep,2) - math.pow(singleDeltaY,2)))
  /**
   * Nilai absolut besar langkah y berdasarkan gradien garis
   */
  private val yStep : Double = math.sqrt(math.abs(math.pow(singleStep,2) - math.pow(singleDeltaX,2)))

  /**
   * Menyimpan posisi x
   */
  private var xCur : Double = from.getX
  /**
   * Menyimpan posisi y
   */
  private var yCur : Double = from.getY

  /**
   * Arah pertambahan x
   */
  private var xIncrementDirection = 0
  /**
   * Arah pertambahan y
   */
  private var yIncrementDirection = 0


  if(from.getX > to.getX){
    xIncrementDirection = -1
  }else if(from.getX < to.getX){
    xIncrementDirection = 1
  }

  if(from.getY > to.getY){
    yIncrementDirection = -1
  }else if(from.getY < to.getY){
    yIncrementDirection = 1
  }

  /**
   * Mengembalikan nilai true atau false terhadap ada dan tidak adanya langkah selanjutnya
   * @return ada tidaknya langkah selanjutnya
   */
  def hasNext : Boolean = {
    var res : Boolean = true
    if(xCur > to.getX && xIncrementDirection == 1){
      res = false
    }
    if(yCur > to.getY && yIncrementDirection == 1){
      res = false
    }
    if(xCur < to.getX && xIncrementDirection == -1){
      res = false
    }
    if(yCur < to.getY && yIncrementDirection == -1){
      res = false
    }
    return res
  }

  /**
   * Mengembalikan nilai benar jika langkah selanjutnya merupakan langkah terakhir
   * @return apakah langkah selanjutnya langkah terakhir
   */
  def isLastIteration : Boolean = {
    var res : Boolean = false
    if(xCur + xStep > to.getX && xIncrementDirection == 1){
      res = true
    }
    if(yCur + yStep > to.getY && yIncrementDirection == 1){
      res = true
    }
    if(xCur - xStep < to.getX && xIncrementDirection == -1){
      res = true
    }
    if(yCur - yStep < to.getY && yIncrementDirection == -1){
      res = true
    }
    return res

  }

  /**
   * Mengiterasi langkah selanjutnya
   * @return ada tidaknya langkah selanjutnya
   */
  def next : Boolean = {
    if(!hasNext) {
      return false
    }else{
      xCur += (xStep * xIncrementDirection)
      yCur += (yStep * yIncrementDirection)
      return true
    }

  }

  /**
   * Mengembalikan nilai absolut besar langkah x berdasarkan gradien garis
   * @return jumlah step x
   */
  def getXStep : Double = {
    return xStep
  }

  /**
   * Mengembalikan nilai absolut besar langkah y berdasarkan gradien garis
   * @return jumlah step y
   */
  def getYStep : Double = {
    return yStep
  }

  /**
   * Mengembalikan posisi x saat ini
   * @return posisi x saat ini
   */
  def getCurrentX : Double = {
    return xCur
  }

  /**
   * Mengembalikan posisi y saat ini
   * @return posisi y saat ini
   */
  def getCurrentY : Double = {
    return yCur
  }

}

