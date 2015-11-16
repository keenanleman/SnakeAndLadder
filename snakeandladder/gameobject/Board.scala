package snakeandladder.gameobject

import java.awt.event.MouseEvent
import java.awt.geom.{RoundRectangle2D, Point2D}
import java.awt.{Graphics2D, Shape, Color, Graphics}
import java.util.ArrayList
import java.util.Random
import java.util.Iterator

import snakeandladder.engine.BoardSettings
import snakeandladder.gameevent.MouseEventListener
import snakeandladder.utility.AssetManager

/**
 * Kelas board, representasi dari papan permainan, kelas ini mepopulasi tile sesuai dengan masukan
 * numRow dan numCol
 * @param initialX posisi awal x
 * @param initialY posisi awal y
 * @param numRow jumlah baris tile dalam board
 * @param numCol jumlah kolom tile dalam board
 */
class Board(initialX : Double, initialY : Double, numRow : Int, numCol : Int)
  extends GameObject(initialX,initialY) with GameObjectUpdate with MouseEventListener{
  /* multidimensional array dari tile */
  private var tiles : Array[Array[Tile]] = Array.ofDim[Tile](numRow,numCol)
  private var tilesRefNum : Array[Tile] = new Array[Tile](numRow * numCol)

  private var tileColorOdd : Color = AssetManager.getColor("TileColorOdd")

  private var tileColorEven : Color = AssetManager.getColor("TileColorEven")

  private var boardBackgroundColor : Color = AssetManager.getColor("BoardBackgroundColor")

  private var boardBackground : RoundRectangle2D.Double = null
  /**
   * Random generator
   */
  private val random : Random = new Random(System.nanoTime())

  /**
   * Array untuk menyimpan object Snake
   */
  private var snakes : Array[Snake] = null

  //private var ladders : Array[Ladder] = null

  private var fullWidth : Double = 0
  private var fullHeight : Double = 0

  /**
   * Dadu yang digunakan board
   */
  private var dice : Dice = null

  /**
   * List dari pemain
   */
  private val players : ArrayList[Player] = new ArrayList[Player]
  /**
   * Iterator dari List pemain
   */
  private val playerIterator : Iterator[Player] = players.iterator()


  /* Menginisiasi board */
  initBoard
  /**
   * Method untuk mempopulasi board dengan tile
   */
  private def initBoard : Unit = {
    var currentX = x
    var currentY = y
    var borderX = x - 16
    var borderY = y - 16
    fullWidth = (numCol * BoardSettings.TILE_SIZE) + 32
    fullHeight = (numRow * BoardSettings.TILE_SIZE) + 32
    boardBackground = new RoundRectangle2D.Double(borderX,borderY,fullWidth,fullHeight,20,20)
    var numOfTiles = numRow * numCol
    var rightToLeft : Boolean = true
    for (i <- 0 until numRow) {
      for (j <- 0 until numCol) {
        var currentColor : Color = null
        if(numOfTiles % 2 == 1){
          currentColor = tileColorOdd
        }else{
          currentColor = tileColorEven
        }
        tiles(i)(j) = new Tile(currentX,currentY,numOfTiles,currentColor)
        tilesRefNum(numOfTiles - 1) = tiles(i)(j)
        numOfTiles -= 1
        if(rightToLeft) {
          currentX += BoardSettings.TILE_SIZE
        }else{
          currentX -= BoardSettings.TILE_SIZE
        }
      }
      currentY += BoardSettings.TILE_SIZE
      if(rightToLeft){
        currentX -= BoardSettings.TILE_SIZE
      }else{
        currentX += BoardSettings.TILE_SIZE
      }
      rightToLeft = !rightToLeft
    }
  }

  /**
   * Menjalankan pemain pada gilirannya(Masih membutuhkan kelas GameStage)
   */
  def turnPlayer : Unit = {
    dice.roll
  }

  def getHeight : Double = fullHeight

  def getWidth : Double = fullHeight

  /**
    * Mengembalikan tile berdasarkan nomor tile
    * @param number nomor tile
    * @return tile yang dipilih berdasarkan nomor tile
    */
  def getTileByNumber(number : Int) : Tile = tilesRefNum(number - 1)

  /**
   * Mengembalikan dadu yang digunakan pada board
   * @return
   */
  def getDice : Dice = dice

  /**
   * Mengembalikan array dari semua pemain yang ada pada board
   * @return array dari pemain
   */
  def getPlayers : Array[Player] = players.toArray(new Array[Player](players.size()))

  /**
   * Mengembalikan array dari semua snake yang ada pada board
   * @return array dari snake
   */
  def getSnakes : Array[Snake]  = snakes


  //def getLadders : Array[Ladder] = ladders

  /**
   * Mengeset dadu pada board
   * @param dice
   */
  def setDice(dice : Dice) : Unit = {
    this.dice = dice
  }

  /**
   * Menambah player kedalam board
   * @param player
   */
  def addPlayer(player : Player) : Unit = {
    players.add(player)
  }

  /**
   * Method untuk mempopulasi board dengan snake
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
   * Method untuk mempopulasi board dengan snake
   *
  def populateLadder(numOfLadder : Int) : Unit = {
    ladders = new Array[Ladder](numOfLadder)
    var from : Int = 0
    var to : Int = 0
    var maxIndex = (numRow * numCol) + 1
    for(i <- 0 until numOfLadder){
      from = 1 + random.nextInt(maxIndex - 1)
      while(getTileByNumber(from).isHasSnake){
        from = 1 + random.nextInt(maxIndex - 1)
      }
      var fromTile : Tile = getTileByNumber(from)
      fromTile.gotLadder

      to = 1 + random.nextInt(maxIndex - 1)
      while(to <= from || getTileByNumber(to).isHasSnake){
        to = 1 + random.nextInt(maxIndex - 1)
      }
      var toTile : Tile = getTileByNumber(to)
      toTile.gotLadder
      ladders(i) = new Ladder(fromTile, toTile)
    }
  }*/

  /**
   * Method render dari board, merender tile-tile yang dibutuhkan board
   * @param graphics graphics dari canvas pada kelas GameDisplay
   */
  override def render(graphics: Graphics) : Unit = {
    /* Test Code (akan di ubah ketika kelas GameState selesai dibuat*/

    graphics.setColor(boardBackgroundColor)
    graphics.asInstanceOf[Graphics2D].fill(boardBackground)
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


