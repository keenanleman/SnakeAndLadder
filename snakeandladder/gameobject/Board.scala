package snakeandladder.gameobject

import java.awt.{Color, Graphics}

/**
 * Kelas board, representasi dari papan permainan, kelas ini mepopulasi tile sesuai dengan masukan
 * numRow dan numCol
 * @param initialX posisi awal x
 * @param initialY posisi awal y
 * @param numRow jumlah baris tile dalam board
 * @param numCol jumlah kolom tile dalam board
 */
class Board(initialX : Double, initialY : Double, numRow : Int, numCol : Int) extends GameObject(initialX,initialY){
  /* multidimensional array dari tile */
  private var tiles : Array[Array[Tile]] = Array.ofDim[Tile](numRow,numCol)

  /**
   * Method untuk mempopulasi board dengan tile
   */
  def initTiles : Unit = {
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
        numOfTiles -= 1
        if(rightToLeft) {
          currentX += Tile.TILE_SIZE + Tile.TILE_BORDER_THICKNESS
        }else{
          currentX -= Tile.TILE_SIZE + Tile.TILE_BORDER_THICKNESS
        }
      }
      currentY += Tile.TILE_SIZE + Tile.TILE_BORDER_THICKNESS
      if(rightToLeft){
        currentX -= Tile.TILE_SIZE + Tile.TILE_BORDER_THICKNESS
      }else{
        currentX += Tile.TILE_SIZE + Tile.TILE_BORDER_THICKNESS
      }
      rightToLeft = !rightToLeft
    }
  }

  /**
   * Method render dari board, merender tile-tile yang dibutuhkan board
   * @param graphics graphics dari canvas pada kelas GameDisplay
   */
  override def render(graphics: Graphics) : Unit = {
    for (i <- 0 until numRow) {
      for (j <- 0 until numCol) {
        tiles(i)(j).render(graphics)
      }
    }
  }
}

