package snakeandladder.gameobject

import java.awt.{Color, Graphics2D, Graphics}
import java.awt.geom.RoundRectangle2D

import snakeandladder.utility.DynamicLineIterator

/**
 * Merepresentasikan pemain
 * @param initialTile tile lokasi awal player
 * @param board board tempat player di mainkan
 */
class Player(initialTile : Tile, board : Board) extends GameObject(initialTile.getX, initialTile.getY) with GameObjectUpdate{
  x += Player.PLAYER_REL_POS_TO_TILE
  y += Player.PLAYER_REL_POS_TO_TILE
  /**
   * Objek yang akan dirender pada board sebagai representasi dari player
   */
  private var playerDrawable : RoundRectangle2D.Double = new RoundRectangle2D.Double(x,y,Player.PLAYER_SIZE,Player.PLAYER_SIZE,20,20)
  /**
   * Posisi tile tempat player di tempatkan
   */
  private var currentTile : Int = initialTile.getTileNumber
  private var followSnake : Snake = null
  private var snakeIterator : DynamicLineIterator = null
  private var diceRolled = false
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
    var playerColor = new Color(Color.BLUE.getRed,Color.BLUE.getGreen,Color.BLUE.getBlue,200)
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
    }else if(followSnake != null && (playerDrawable.x != x && playerDrawable.y != y)){
      playerDrawable.x = x
      playerDrawable.y = y
    }else if(playerDrawable.x < x) {
      playerDrawable.x += Player.MOVE_SPEED
    }else if(playerDrawable.x > x) {
      playerDrawable.x -= Player.MOVE_SPEED
    }else if(playerDrawable.y > y) {
      playerDrawable.y -= Player.MOVE_SPEED
    }else if(followSnake != null) {
      moveToFollowSnake
    }else if(destinationTile > currentTile){
      moveToNextTile
    }
  }

  /**
   * Memindahkan posisi player ke tile selanjutnya
   */
  def moveToNextTile : Unit = {
    currentTile += 1
    x = board.getTileByNumber(currentTile).getX + Player.PLAYER_REL_POS_TO_TILE
    y = board.getTileByNumber(currentTile).getY + Player.PLAYER_REL_POS_TO_TILE
  }

  /**
    * Memindahkah posisi player mengikuti snake
    */
  def moveToFollowSnake : Unit = {
    if(!snakeIterator.hasNext){
      x = snakeIterator.getPoint.getX - Player.PLAYER_REL_POS_TO_TILE
      y = snakeIterator.getPoint.getY - Player.PLAYER_REL_POS_TO_TILE
      snakeIterator.next
    }else{
      x = followSnake.getTo.getX + Player.PLAYER_REL_POS_TO_TILE
      y = followSnake.getTo.getY + Player.PLAYER_REL_POS_TO_TILE
      playerDrawable.x = x
      playerDrawable.y = y
      currentTile = destinationTile
      followSnake = null
    }
  }

  def getCurrentTile : Int = currentTile

  /**
   * Memindahkan player ke tile tertentu
   * @param to tile yang di tuju
   */
  def moveToTile(to : Tile) : Unit = {
    destinationTile = to.getTileNumber
  }
}

object Player{
  /* Ukuran player pada board*/
  val PLAYER_SIZE : Double = 32
  /* Kecepatan gerakan player pada board */
  val MOVE_SPEED : Double = 4
  /* Posisi player relatif terhadap tile*/
  val PLAYER_REL_POS_TO_TILE : Double = (Tile.TILE_SIZE - Player.PLAYER_SIZE) / 4
}
