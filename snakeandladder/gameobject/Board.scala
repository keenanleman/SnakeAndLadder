package snakeandladder.gameobject

import java.awt.event.MouseEvent
import java.awt.geom.Point2D
import java.awt.{Shape, Color, Graphics}
import java.util.ArrayList
import java.util.Random
import java.util.Iterator

import snakeandladder.gameevent.MouseEventListener

/**
 * Kelas board, representasi dari papan permainan, kelas ini mepopulasi tile sesuai dengan masukan
 * numRow dan numCol
 * @param initialX posisi awal x
 * @param initialY posisi awal y
 * @param numRow jumlah baris tile dalam board
 * @param numCol jumlah kolom tile dalam board
 */
class Board(initialX : Double, initialY : Double, numRow : Int, numCol : Int) extends GameObject(initialX,initialY) with GameObjectUpdate with MouseEventListener{
  /* multidimensional array dari tile */
  private var tiles : Array[Array[Tile]] = Array.ofDim[Tile](numRow,numCol)
  private var tilesRefNum : Array[Tile] = new Array[Tile](numRow * numCol)
  /**
   * Random generator
   */
  private val random : Random = new Random(System.nanoTime())

  /**
   * Array untuk menyimpan object Snake
   */
  private var snakes : Array[Snake] = null 

  private var dice : Dice = null

  private val players : ArrayList[Player] = new ArrayList[Player]
  private val playerIterator : Iterator[Player] = players.iterator()


  /* Menginisiasi board */
  initBoard
  /**
   * Method untuk mempopulasi board dengan tile
   */
  private def initBoard : Unit = {
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

  def turnPlayer : Unit = {
    dice.roll
  }

  /**
    * Mengembalikan tile berdasarkan nomor tile
    * @param number nomor tile
    * @return tile yang dipilih berdasarkan nomor tile
    */
  def getTileByNumber(number : Int) : Tile = tilesRefNum(number - 1)

  def getDice : Dice = dice

  def getPlayers : Array[Player] = players.toArray(new Array[Player](players.size()))

  def getSnakes : Array[Snake]  = snakes

  def setDice(dice : Dice) : Unit = {
    this.dice = new Dice(numRow * Tile.TILE_SIZE,0)
  }
  def addPlayer(player : Player) : Unit = {
    players.add(player)
  }

  /**
   * Method untuk mempopulasi board dengan tile
   */
  def populateSnake(numOfSnake : Int) : Unit = {
    snakes = new Array[Snake](numOfSnake)
    var from : Int = 0
    var to : Int = 0
    var maxIndex = (numRow * numCol) + 1
    for(i <- 0 until numOfSnake){
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
  }

  override def triggeredMouseEvent(event: MouseEvent): Unit = {
    if(dice.getDrawable.contains(new Point2D.Double(event.getX,event.getY))){
      if(MouseEvent.getMouseModifiersText(event.getModifiersEx).equals("Button1")){
        turnPlayer
      }
    }
  }

  /**
   * Method update yang harus di implementasikan semua GameObject yang mampu mengupdate
   * data
   */
  override def update(): Unit = {
    if(dice.getCurrentValue != 0){
      players.get(0).moveToTile(getTileByNumber(players.get(0).getCurrentTile + dice.getCurrentValue))
      dice.resetDice
    }
  }
}


