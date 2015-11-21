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
  private var playerColors : Array[Color] = Array[Color](
    Color.BLUE,
    Color.GREEN,
    Color.RED,
    Color.GRAY,
    Color.ORANGE,
    AssetManager.getColor("BlueVioletColor"),
    AssetManager.getColor("OliveColor"),
    AssetManager.getColor("SepiaColor"))

  /**
   * Indeks array dari pemain yang sedang mendapat giliran
   */
  private var currentPlayerNumber : Int = 0

  /**
   * Menyimpan pemain yang sedang mendapat giliran
   */
  private var currentPlayer : Player = null

  /**
   * Jumlah pemain sebenarnya jika ditambah pemain yang di gerakan komputer
   */
  private var actualNumOfPlayer : Int = numberOfPlayer

  /**
   * Array yang menyimpan objek kelas Player yang merepresentasikan pemain
   */
  private var players : Array[Player] = _

  /* Jika player bermain sendirian maka akan di tambah 1 pemain yang di gerakan komputer */
  if(actualNumOfPlayer == 1){
    players = new Array[Player](2)
    players(1) = new Player(board.getTileByNumber(1),playerColors(1),board)
    players(1).setComputerPlayer(true)
    /* Menginstansiasi player yang dibutuhkan*/
    for(i <- 0 until actualNumOfPlayer){
      players(i) = new Player(board.getTileByNumber(1),playerColors(i),board)
    }
    actualNumOfPlayer += 1
  }else{
    players = new Array[Player](actualNumOfPlayer)
    /* Menginstansiasi player yang dibutuhkan*/
    for(i <- 0 until actualNumOfPlayer){
      players(i) = new Player(board.getTileByNumber(1),playerColors(i),board)
    }
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
    currentPlayerNumber = (currentPlayerNumber + 1)%(actualNumOfPlayer)
    return currentPlayer
  }

  /**
   * Mengembalikan pemain yang mendapat giliran
   * tanpa menjadikan pemain selanjutnya sebagai
   * pemain yang mendapat giliran
   * @return pemain yang mendapat giliran
   */
  def getCurrentPlayer : Player = players(currentPlayerNumber)

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

