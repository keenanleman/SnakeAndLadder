package snakeandladder.gameobject

import java.awt.{Color, Graphics}
import java.util.Random

/**
 * Kelas board, representasi dari papan permainan, kelas ini mepopulasi tile sesuai dengan masukan
 * numRow dan numCol
 * @param initialX posisi awal x
 * @param initialY posisi awal y
 * @param numRow jumlah baris tile dalam board
 * @param numCol jumlah kolom tile dalam board
 */
class Board(initialX : Double, initialY : Double, numRow : Int, numCol : Int) extends GameObject(initialX,initialY) with GameObjectUpdate{
  /* multidimensional array dari tile */
  private var tiles : Array[Array[Tile]] = Array.ofDim[Tile](numRow,numCol)
  private var tilesRefNum : Array[Tile] = new Array[Tile](numRow * numCol)

  /* Test Code (akan di ubah ketika kelas GameState selesai dibuat*/
  private var dice : Dice = new Dice(numRow * Tile.TILE_SIZE,0)

  /**
    * Random generator
    */
  private val random : Random = new Random(System.nanoTime())

  /**
    * Array untuk menyimpan object Snake
    */
  private var snakes : Array[Snake] = new Array[Snake](Board.numSnake)

  /* Test Code (akan di ubah ketika kelas GameState selesai dibuat*/
  var player : Player = _


  /* Menginisiasi board */
  initBoard

  /**
    * Method untuk menginisiasi Board
    */
  def initBoard : Unit = {
    initTiles
    initSnake
    player = new Player(getTileByNumber(1),this)
    player.moveToTile(tiles(0)(9))
  }

  /**
    * Method untuk mempopulasi board dengan tile
    */
  private def initSnake : Unit = {
    var from : Int = 0
    var to : Int = 0
    var maxIndex = (numRow * numCol) + 1
    for(i <- 0 until Board.numSnake){
      from = 1 + random.nextInt(maxIndex - 1)
      while(getTileByNumber(from).isHasSnake){
        from = 1 + random.nextInt(maxIndex - 1)
      }
      var fromTile : Tile = getTileByNumber(from)
      fromTile.gotSnake

      to = 1 + random.nextInt(maxIndex - 1)
      while(to >= from || getTileByNumber(to).isHasSnake){
        to = 1 + random.nextInt(maxIndex - 1)
      }
      var toTile : Tile = getTileByNumber(to)
      toTile.gotSnake
      snakes(i) = new Snake(fromTile, toTile)
      fromTile.setSnakeHead(snakes(i))
    }
  }

  /**
   * Method untuk mempopulasi board dengan tile
   */
  private def initTiles : Unit = {
    var currentX = x
    var currentY = y
    var numOfTiles = numRow * numCol
    var rightToLeft : Boolean = true
    for (i <- 0 until numRow) {
      for (j <- 0 until numCol) {
        var currentColor : Color = null
        if(numOfTiles % 2 == 1){
          currentColor = Tile.TILE_COLOR_ODD
        }else{
          currentColor = Tile.TILE_COLOR_EVEN
        }
        tiles(i)(j) = new Tile(currentX,currentY,numOfTiles,currentColor)
        tilesRefNum(numOfTiles - 1) = tiles(i)(j)
        numOfTiles -= 1
        if(rightToLeft) {
          currentX += Tile.TILE_SIZE
        }else{
          currentX -= Tile.TILE_SIZE
        }
      }
      currentY += Tile.TILE_SIZE
      if(rightToLeft){
        currentX -= Tile.TILE_SIZE
      }else{
        currentX += Tile.TILE_SIZE
      }
      rightToLeft = !rightToLeft
    }
  }

  /**
    * Mengembalikan tile berdasarkan nomor tile
    * @param number nomor tile
    * @return tile yang dipilih berdasarkan nomor tile
    */
  def getTileByNumber(number : Int) : Tile = tilesRefNum(number - 1)

  /**
   * Method render dari board, merender tile-tile yang dibutuhkan board
   * @param graphics graphics dari canvas pada kelas GameDisplay
   */
  override def render(graphics: Graphics) : Unit = {
    /* Test Code (akan di ubah ketika kelas GameState selesai dibuat*/
    for (i <- 0 until numRow) {
      for (j <- 0 until numCol) {
        tiles(i)(j).render(graphics)
      }
    }
    for(i <- 0 until Board.numSnake){
      snakes(i).render(graphics)
    }
    player.render(graphics)
    dice.render(graphics)
  }

  /**
   * Method update pada board akan mengupdate semua GameObject yang merupakan
   * komposisi dari pada Kelas board
   */
  override def update(): Unit = {
    /* Test Code (akan di ubah ketika kelas GameState selesai dibuat*/
    player.update()
  }
}


object Board{
  /**
   * Banyak ular dalam sebuah board
   */
  val numSnake : Int = 5
}
