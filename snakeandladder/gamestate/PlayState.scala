package snakeandladder.gamestate

import java.awt.image.BufferedImage
import java.awt._
import java.awt.geom.{Rectangle2D, RoundRectangle2D}
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import snakeandladder.engine.{ButtonSettings, GameDisplaySettings}
import snakeandladder.gamecomponent.{Background, ButtonAction, Button,Panel}
import snakeandladder.gameobject._
import snakeandladder.utility.AssetManager

class PlayState(stateTitle : String, board : Board)
  extends GameState(stateTitle) {
  /**
   * Method abstract untuk inisiasi GameState
   */
  override def init: Unit = {
    var background : Background = new Background(AssetManager.getImage("BackgroundWood"))

    var controlPanel : Panel = new Panel(board.getX + board.getWidth,board.getY - 16,200,board.getHeight)

    board.setDice(new Dice(controlPanel.getX + 25, controlPanel.getY + 25))

    var newGameButton : Button = new Button(
      controlPanel.getX + 100 - 50
      , board.getHeight - 100, "New Game", 100, ButtonSettings.BUTTON_HEIGHT
    )
    var exitToMainMenu : Button = new Button(
      controlPanel.getX + 100 - 50
      , board.getHeight - 50, "Main Menu", 100, ButtonSettings.BUTTON_HEIGHT
    )
    exitToMainMenu.setAction(new ButtonAction {
      /**
       * Method yang akan dipanggil saat tombol ditekan
       */
      override def buttonClicked: Unit = {
        GameStateManager.setActiveState("PrePlayState")
      }
    })

    addComponentObject(background)
    addComponentObject(controlPanel)
    addComponentObject(board)
    addComponentObject(board.getDice)
    addComponentObjects(board.getPlayers.asInstanceOf[Array[GameObject]])
    addComponentObjects(board.getSnakes.asInstanceOf[Array[GameObject]])
    //addComponentObjects(board.getLadders.asInstanceOf[Array[GameObject]])
    addComponentObject(newGameButton)
    addComponentObject(exitToMainMenu)
  }
}
