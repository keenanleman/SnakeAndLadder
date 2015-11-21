package snakeandladder.gameobject

import java.awt.event.MouseEvent
import java.awt.geom.{RoundRectangle2D, Point2D}
import java.awt.{Graphics2D, Shape, Color, Graphics}
import java.util.ArrayList
import java.util.Random
import java.util.Iterator

import snakeandladder.engine.BoardSettings
import snakeandladder.gameevent.MouseEventListener
import snakeandladder.gamestate.GameStage
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
  /**
   * multidimensional array dari tile
   */
  private var tiles : Array[Array[Tile]] = Array.ofDim[Tile](numRow,numCol)
  /**
   * Array yang mengandung objek yang sama dari array tiles,
   * namun di buat agar dapat di sesuaikan dengan nomor tile pada game
   */
  private var tilesRefNum : Array[Tile] = new Array[Tile](numRow * numCol)

  /**
   * Warna dari tile ganjil
   */
  private var tileColorOdd : Color = AssetManager.getColor("TileColorOdd")

  /**
   * Warna dari tile genap
   */
  private var tileColorEven : Color = AssetManager.getColor("TileColorEven")

  /**
   * Warna dari background
   */
  private var boardBackgroundColor : Color = AssetManager.getColor("BoardBackgroundColor")

  /**
   * Background dari board
   */
  private var boardBackground : RoundRectangle2D.Double = null
  /**
   * Random generator
   */
  private val random : Random = new Random(System.nanoTime())

  /**
   * GameStage yang digunakan pada game
   */
  private var gameStage : GameStage = null
  /**
   * Array untuk menyimpan object Snake
   */
  private var snakes : Array[Snake] = null

  /**
   * Array tangga yang digunakan pada board
   */
  private var ladders : Array[Ladder] = null

  /**
   * Lebar board + border(dibuat diatas panel)
   */
  private var fullWidth : Double = 0
  /**
   * Tinggi board + border(dibuat diatas panel)
   */
  private var fullHeight : Double = 0

  /**
   * Apakah masih ada animasi yang berjalan
   */
  private var animationRolling = false

  /**
   * Dadu yang digunakan board
   */
  private var dice : Dice = null

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
   * Digunakan untuk mengeset GameStage
   * @param gameStage game stage yang digunakan
   */
  def setGameStage(gameStage : GameStage)  : Unit = {
    this.gameStage = gameStage
  }

  /**
   * Mengembalikan GameStage yang digunakan board
   * @return GameStage yang digunakan
   */
  def getGameStage : GameStage = gameStage

  /**
   * Menjalankan pemain pada gilirannya(Masih membutuhkan kelas GameStage)
   */
  def turnPlayer : Unit = {
    dice.roll
  }

  /**
   * Mengembalikan tinggi dari board
   * @return tinggi dari board
   */
  def getHeight : Double = fullHeight

  /**
   * Mengembalikan lebar dari board
   * @return lebar dari board
   */
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
   * Mengembalikan array dari semua snake yang ada pada board
   * @return array dari snake
   */
  def getSnakes : Array[Snake]  = snakes

  /**
   * Mengembalikan array dari semua ladder yang ada pada board
   * @return array dari ladder
   */
  def getLadders : Array[Ladder] = ladders

  /**
   * Mengeset dadu pada board
   * @param dice
   */
  def setDice(dice : Dice) : Unit = {
    this.dice = dice
  }

  /**
   * Method untuk mempopulasi board dengan snake
   * @param numOfSnake jumlah dari ular
   */
  def populateSnake(numOfSnake : Int) : Unit = {
    snakes = new Array[Snake](numOfSnake)
    var from : Int = 0
    var to : Int = 0
    var maxIndex = (numRow * numCol) + 1
    for(i <- 0 until numOfSnake){
      from = 1 + random.nextInt(maxIndex - 1)
      while(getTileByNumber(from).isHasSnake || getTileByNumber(from).isHasLadder ||  from == 100 || from == 1){
        from = 1 + random.nextInt(maxIndex - 1)
      }
      to = 1 + random.nextInt(maxIndex - 1)
      while(to == from || getTileByNumber(to).isHasSnake || getTileByNumber(to).isHasLadder ){
        to = 1 + random.nextInt(maxIndex - 1)
      }
      if(from < to){
        var tmp = from
        from = to
        to = tmp
      }
      var fromTile : Tile = getTileByNumber(from)
      fromTile.gotSnake
      var toTile : Tile = getTileByNumber(to)
      toTile.gotSnake
      snakes(i) = new Snake(fromTile, toTile)
      fromTile.setSnakeHead(snakes(i))
    }
  }

  /**
   * Method untuk mempopulasi board dengan tangga/ladder
   * @param numOfLadder jumlah dari tangga
   */
  def populateLadder(numOfLadder : Int) : Unit = {
    ladders = new Array[Ladder](numOfLadder)
    var from : Int = 0
    var to : Int = 0
    var maxIndex = (numRow * numCol) + 1
    var i : Int = 0
    while(i < numOfLadder){
      from = 1 + random.nextInt(maxIndex - 1)
      while(getTileByNumber(from).isHasLadder ||getTileByNumber(from).isHasSnake || from == 100 || from == 1){
        from = 1 + random.nextInt(maxIndex - 1)
      }
      to = 1 + random.nextInt(maxIndex - 1)
      while(to == from || getTileByNumber(to).isHasLadder || getTileByNumber(to).isHasSnake || to == 100 || to == 1 ){
        to = 1 + random.nextInt(maxIndex - 1)
      }
      if(from > to){
        var tmp = from
        from = to
        to = tmp
      }
      var fromTile : Tile = getTileByNumber(from)
      var toTile : Tile = getTileByNumber(to)
      var gradien = math.abs(fromTile.getY - toTile.getY) / math.abs(fromTile.getX - toTile.getX)
      if(gradien <= 2 && gradien >= 1){
        fromTile.gotLadder
        toTile.gotLadder
        ladders(i) = new Ladder(fromTile, toTile)
        fromTile.setLadder(ladders(i))
        i += 1
      }
    }
  }

  /**
   * Method render dari board, merender tile-tile yang dibutuhkan board
   * @param graphics graphics dari canvas pada kelas GameDisplay
   */
  override def render(graphics: Graphics) : Unit = {
    graphics.setColor(boardBackgroundColor)
    graphics.asInstanceOf[Graphics2D].fill(boardBackground)
    for (i <- 0 until numRow) {
      for (j <- 0 until numCol) {
        tiles(i)(j).render(graphics)
      }
    }
  }

  /**
   * Method yand dipanggil ketika suatu event di broadcast pada board
   * @param event event yand dibroadcast
   */
  override def triggeredMouseEvent(event: MouseEvent): Unit = {
    if(dice.getDrawable.contains(new Point2D.Double(event.getX,event.getY))){
      if(MouseEvent.getMouseModifiersText(event.getModifiersEx).equals("Button1")){
        if(!gameStage.isWin) {
          turnPlayer
        }
      }
    }
  }

  /**
   * Method update yang harus di implementasikan semua GameObject yang mampu mengupdate
   * data
   */
  override def update(): Unit = {
    var currentPlayer = gameStage.getCurrentPlayer
    if(dice.getCurrentValue != 0){
      animationRolling = true
      var nextTile = currentPlayer.getCurrentTile + dice.getCurrentValue
      var bounceBack = 0
      if(nextTile > 100){
        bounceBack = nextTile - 100
        currentPlayer.bounceTo100(bounceBack)
      } else{
        currentPlayer.moveToTile(getTileByNumber(nextTile))
      }
      dice.resetDice
    }else if(currentPlayer.isComputerPlayer && !animationRolling){
      turnPlayer
    }
    if(currentPlayer.isFinishedTurn && animationRolling){
       animationRolling = false
       gameStage.nextTurn
    }
  }
}


