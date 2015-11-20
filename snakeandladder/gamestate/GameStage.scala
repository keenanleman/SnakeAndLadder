package snakeandladder.gamestate

import java.awt.Color

import snakeandladder.gameobject.{Board, Player}
import snakeandladder.utility.AssetManager

/**
 * Kelas yang mengatur tentang giliran jalan pemain-pemain,
 * serta mempopulasi pemain-pemain yang ikut bermain dalam
 * game
 * @param numberOfPlayer jumlah pemain yang ikut bermain
 */
class GameStage(numberOfPlayer : Int, board : Board) {
  /**
   * Array dari warna-warna yang digunakan untuk pemain
   */
  private var playersColors : Array[Color] = Array[Color](
    Color.BLUE,
    Color.GREEN,
    Color.RED,
    Color.GRAY,
    Color.ORANGE,
    AssetManager.getColor("BlueVioletColor"),
    AssetManager.getColor("OliveColor"))

  /**
   * Indeks array dari pemain yang sedang mendapat giliran
   */
  private var currentPlayerNumber : Int = 0

  /**
   * Menyimpan pemain yang sedang mendapat giliran
   */
  private var currentPlayer : Player = null

  /**
   * Array yang menyimpan objek kelas Player yang merepresentasikan pemain
   */
  private var players : Array[Player] = new Array[Player](numberOfPlayer)

  /* Menginstansiasi player yang dibutuhkan*/
  for(i <- 0 until numberOfPlayer){
    players(i) = new Player(board.getTileByNumber(1),playersColors(i),board)
  }

  /**
   * Mengembalikan array dari semua pemain yang ada GameStage
   * @return array dari pemain
   */
  def getPlayers : Array[Player] = players

  /**
   * Mengembalikan pemain yang mendapat giliran
   * @return pemain yang mendapat giliran
   */
  def nextTurn : Player = {
    currentPlayer = players(currentPlayerNumber)
    currentPlayerNumber = (currentPlayerNumber + 1)%(numberOfPlayer)
    return currentPlayer
  }

  /**
   * Mengembalikan nomor pemain yang mendapat giliran
   * @return nomor pemain yang mendapat giliran
   */
  def getCurrentPlayerNumber : Int = currentPlayerNumber + 1

  /**
   * Mengembalikan status permainan, apakah sudah dimenangkan atau belum
   * @return true jika permainan sudah selesai, dan false jika sebaliknya
   */
  def isWin : Boolean = {
    var status : Boolean = false
    if(currentPlayer != null && currentPlayer.getCurrentTile == 100){
      status = true
    }
    return status
  }
}

