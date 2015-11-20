package snakeandladder.gameobject

import java.awt.{Color, Graphics2D, Graphics}
import java.awt.geom.RoundRectangle2D

import snakeandladder.engine.PlayerSettings
import snakeandladder.utility.DynamicLineIterator

/**
 * Merepresentasikan pemain
 * @param initialTile tile lokasi awal player
 * @param board board tempat player di mainkan
 */
class Player(initialTile : Tile,playerColor : Color, board : Board) extends GameObject(initialTile.getX, initialTile.getY) with GameObjectUpdate{
  x += PlayerSettings.PLAYER_REL_POS_TO_TILE
  y += PlayerSettings.PLAYER_REL_POS_TO_TILE
  /**
   * Objek yang akan dirender pada board sebagai representasi dari player
   */
  private var playerDrawable : RoundRectangle2D.Double = new RoundRectangle2D.Double(x,y,PlayerSettings.PLAYER_SIZE,PlayerSettings.PLAYER_SIZE,20,20)

  /**
   * Posisi tile tempat player di tempatkan
   */
  private var currentTile : Int = initialTile.getTileNumber

  /**
   * Snake yang sedang di ikuti oleh player
   */
  private var followSnake : Snake = null
  
  /**
   * Ladder yang sedang di ikuti oleh player
   */
  private var followLadder : Ladder = null

  /**
   * Iterator yang mengikuti bentuk snake yang bergelombang
   */
  private var snakeIterator : DynamicLineIterator = null

  /**
   * Iterator yang dapat mengikuti bentuk tangga
   */
  private var ladderIterator :DynamicLineIterator = null

  /**
   * Status apakah dadu telah di lempar
   */
  private var diceRolled = false

  /**
   * Sisa nilai dadu ketika pemain mencapai tile ke 100
   */
  private var bounceBack = 0

  /**
   * Posisi tempat player akan dipindahkan
   */
  private var destinationTile : Int = initialTile.getTileNumber

  /**
   * Setiap objek/benda didalam game memiliki method render yang digunakan untuk
   * merender dirinya sendiri
   * @param graphics graphics dari canvas pada kelas GameDisplay
   */
  override def render(graphics: Graphics): Unit = {
    var g2d : Graphics2D = graphics.asInstanceOf[Graphics2D]
    g2d.setColor(playerColor)
    g2d.fill(playerDrawable)
  }

  /**
   * Method update pada player yang digunakan untuk mengupdate data, posisi, dll pada saat suatu event
   * terjadi saat runtime
   */
  override def update(): Unit = {
    var tile : Tile = board.getTileByNumber(currentTile)
    if(tile.isHasSnake && tile.getSnakeHead != null && followSnake == null && currentTile == destinationTile){
      followSnake = tile.getSnakeHead
      destinationTile = followSnake.getTo.getTileNumber
      snakeIterator = new DynamicLineIterator(followSnake.getDrawable)
    }else if(tile.isHasLadder && tile.getLadder != null && followLadder == null && currentTile == destinationTile){
      followLadder = tile.getLadder
      destinationTile = followLadder.getTo.getTileNumber
      ladderIterator = new DynamicLineIterator(followLadder.getDrawable)
    }else if(followLadder != null && (playerDrawable.x != x && playerDrawable.y != y)){
      playerDrawable.x = x
      playerDrawable.y = y
    }else if(followSnake != null && (playerDrawable.x != x && playerDrawable.y != y)){
      playerDrawable.x = x
      playerDrawable.y = y
    }else if(playerDrawable.x < x) {
      playerDrawable.x += PlayerSettings.MOVE_SPEED
    }else if(playerDrawable.x > x) {
      playerDrawable.x -= PlayerSettings.MOVE_SPEED
    }else if(playerDrawable.y > y) {
      playerDrawable.y -= PlayerSettings.MOVE_SPEED
    }else if(followLadder != null) {
      moveToFollowLadder
    }else if(followSnake != null) {
      moveToFollowSnake
    }else if(destinationTile > currentTile){
      moveToNextTile
    }else if(bounceBack > 0){
      bounceBack -= 1
      destinationTile -= 1
      moveToPrevTile
    }
  }

  /**
   * Memindahkan posisi player ke tile selanjutnya
   */
  def moveToNextTile : Unit = {
    currentTile += 1
    x = board.getTileByNumber(currentTile).getX + PlayerSettings.PLAYER_REL_POS_TO_TILE
    y = board.getTileByNumber(currentTile).getY + PlayerSettings.PLAYER_REL_POS_TO_TILE
  }

  /**
   * Memindahkan posisi player ke tile sebelumnya
   */
  def moveToPrevTile : Unit = {
    currentTile -= 1
    x = board.getTileByNumber(currentTile).getX + PlayerSettings.PLAYER_REL_POS_TO_TILE
    y = board.getTileByNumber(currentTile).getY + PlayerSettings.PLAYER_REL_POS_TO_TILE
  }

  /**
    * Memindahkah posisi player mengikuti snake
    */
  def moveToFollowSnake : Unit = {
    if(!snakeIterator.hasNext){
      snakeIterator.next
      x = snakeIterator.getPoint.getX - PlayerSettings.PLAYER_REL_POS_TO_TILE
      y = snakeIterator.getPoint.getY - PlayerSettings.PLAYER_REL_POS_TO_TILE
    }else{
      x = followSnake.getTo.getX + PlayerSettings.PLAYER_REL_POS_TO_TILE
      y = followSnake.getTo.getY + PlayerSettings.PLAYER_REL_POS_TO_TILE
      playerDrawable.x = x
      playerDrawable.y = y
      currentTile = destinationTile
      followSnake = null
    }
  }

  /**
    * Memindahkah posisi player mengikuti tangga
    */
  def moveToFollowLadder : Unit = {
    if(!ladderIterator.hasNext){
      ladderIterator.next
      x = ladderIterator.getPoint.getX - PlayerSettings.PLAYER_REL_POS_TO_TILE
      y = ladderIterator.getPoint.getY - PlayerSettings.PLAYER_REL_POS_TO_TILE
    }else{
      x = followLadder.getTo.getX + PlayerSettings.PLAYER_REL_POS_TO_TILE
      y = followLadder.getTo.getY + PlayerSettings.PLAYER_REL_POS_TO_TILE
      playerDrawable.x = x
      playerDrawable.y = y
      currentTile = destinationTile
      followLadder = null
    }
  }

  /**
    * Mengembalikan tile yang sedang ditempat player
    * @return tile yang ditempati player
    */
  def getCurrentTile : Int = currentTile

  /**
   * Memindahkan player ke tile tertentu
   * @param to tile yang di tuju
   */
  def moveToTile(to : Tile) : Unit = {
    destinationTile = to.getTileNumber
  }

  /* Memindahkan player ke tile 100, dan jika nilai dadu masih bersisi maka
   * player akan berbalik arah sebanyak nilai dadu yang tersisa
   * @param bounceBack jumlah nilai dadu yang tersisa
   */
  def bounceTo100(bounceBack : Int) : Unit = {
    this.bounceBack = bounceBack
    destinationTile = 100
  }
}

