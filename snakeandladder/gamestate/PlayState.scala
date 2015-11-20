package snakeandladder.gamestate

import java.awt.image.BufferedImage
import java.awt.geom.{Rectangle2D, RoundRectangle2D}

import snakeandladder.engine.{ButtonSettings, GameDisplaySettings,DiceSettings}
import snakeandladder.gamecomponent.{Background, ButtonAction, Button,Panel,Label,IntegerLabel,DataBindAction}
import snakeandladder.gameobject._
import snakeandladder.utility.AssetManager

/**
 * Kelas yang menjadi state/keadaan saat permainan telah dimulai,
 * pada state ini kelas Board,Player(s),Snake(s), Ladder(s), dan Dice
 * akan di-render sebagai komponen-komponen utama dalam permainan ini
 *
 * @param stateTitle Judul/Nama dari state
 */
class PlayState(stateTitle : String, board : Board)
  extends GameState(stateTitle) {
  /**
   * Method abstract(Overrided untuk PlayState) untuk inisiasi GameState
   */
  override def init: Unit = {

    /* Menginstansiasi komponen yang menjadi background di state ini */
    var background : Background = new Background(AssetManager.getImage("BackgroundWood"))

    /* Menginstansiasi komponen yang panel pengontrol */
    var controlPanel : Panel = new Panel(board.getX + board.getWidth,board.getY - 16,200,board.getHeight)

    /* Mengeset dice pada board */
    board.setDice(new Dice(controlPanel.getX + 25, controlPanel.getY + 25))

    /* Menginstansiasi komponen-komponen untuk menginformasikan giliran pemain */
    var playerLabel : Label = 
      new Label(controlPanel.getX + 25,board.getDice.getY + DiceSettings.DICE_SIZE + 50, "Next Turn: ")
    var playerNumberLabel : Label = 
      new Label(controlPanel.getX + 115,
        board.getDice.getY + DiceSettings.DICE_SIZE + 50,
        "Player " + board.getGameStage.getCurrentPlayerNumber.toString
      )

    /* Menginstansiasi tombol 'New Game' */
    var newGameButton : Button = new Button(
      controlPanel.getX + 100 - 50
      , board.getHeight - 100, "New Game", 100, ButtonSettings.BUTTON_HEIGHT
    )

    /* Menginstansiasi tombol 'Main Menu' */
    var exitToMainMenu : Button = new Button(
      controlPanel.getX + 100 - 50
      , board.getHeight - 50, "Main Menu", 100, ButtonSettings.BUTTON_HEIGHT
    )

    /* Mengeset aksi untuk tombol 'New Game' */
    newGameButton.setAction(new ButtonAction {
      /**
       * Method yang akan dipanggil saat tombol ditekan
       */
      override def buttonClicked: Unit = {
        GameStateManager.setActiveState("PrePlayState")
      }
    })

    /* Mengeset aksi untuk tombol 'Main Menu' */
    exitToMainMenu.setAction(new ButtonAction {
      /**
       * Method yang akan dipanggil saat tombol ditekan
       */
      override def buttonClicked: Unit = {
        GameStateManager.setActiveState("MainMenuState")
      }
    })

    playerNumberLabel.setData(new DataBindAction{
      override def updateData : Unit = {
        playerNumberLabel.setText("Player " + board.getGameStage.getCurrentPlayerNumber.toString)
      }
    })


    /* Menambah semua komponen ke state */
    addComponentObject(background)
    addComponentObject(controlPanel)
    addComponentObject(board)
    addComponentObject(board.getDice)
    addComponentObject(playerLabel)
    addComponentObject(playerNumberLabel)
    addComponentObjects(board.getPlayers.asInstanceOf[Array[GameObject]])
    addComponentObjects(board.getSnakes.asInstanceOf[Array[GameObject]])
    addComponentObjects(board.getLadders.asInstanceOf[Array[GameObject]])
    addComponentObject(newGameButton)
    addComponentObject(exitToMainMenu)
  }
}
