package snakeandladder.gamestate

import snakeandladder.engine.{GameRulesSettings, ButtonSettings, GameDisplaySettings, GameEngineSettings}
import snakeandladder.gamecomponent._
import snakeandladder.gameobject.{Player, Dice, Board}
import snakeandladder.utility.AssetManager

/**
 * Kelas yang menjadi state/keadaan saat permainan akan dimulai,
 * pemain dapat memilih jumlah pemain yang akan ikut bermain,
 * memilih jumlah ular pada board,
 * memilih jumalh tangga pada board
 *
 * @param stateTitle Judul/Nama dari state
 */
class PrePlayState(stateTitle : String) extends GameState(stateTitle){
  /**
   * Method abstract(overrided untuk PrePlayState) untuk inisiasi GameState
   */
  override def init: Unit = {
    /* Instansiasi komponen pembentuk background pada state */
    var background : Background = new Background(AssetManager.getImage("BackgroundWood"))
    var backgroundBlur : BackgroundColor = new BackgroundColor(AssetManager.getColor("BackgroundBlurColor"))

    /* Instansiasi komponen pembentuk panel */
    var middlePanelHeight : Double = 200
    var middlePanelWidth : Double = GameDisplaySettings.WINDOW_WIDTH
    var middlePanelX : Double = 0
    var middlePanelY : Double = (GameDisplaySettings.WINDOW_HEIGHT/2) - (middlePanelHeight/2)
    var middlePanel : Panel = new Panel(middlePanelX,middlePanelY,middlePanelWidth,middlePanelHeight)


    /* Instansiasi komponen tombol playButton */
    var playButtonX = (middlePanelWidth / 2) - 50
    var playButtonY = middlePanelY + middlePanelHeight - ButtonSettings.BUTTON_HEIGHT - 16
    var playButton : Button = new Button(playButtonX,playButtonY,"Start!",100,ButtonSettings.BUTTON_HEIGHT)


    /* Instansiasi komponen untuk mengontrol jumlah player */
    var numOfPlayer : Int = 2;
    var numOfPlayerTitleLabel : Label =
      new Label(300,middlePanelY + 20,"Number Of Player :")
    var minPlayer : Button = new Button(450,middlePanelY + 20, "-", 25,ButtonSettings.SMALL_BUTTON_HEIGHT)
    var numOfPlayerLabel : IntegerLabel =
      new IntegerLabel(480,middlePanelY + 20,numOfPlayer.toString)
    var plusPlayer : Button = new Button(515,middlePanelY + 20, "+", 25,ButtonSettings.SMALL_BUTTON_HEIGHT)


    /* Instansiasi komponen untuk mengontrol jumlah ular */
    var numOfSnake : Int = 5;
    var numOfSnakeTitleLabel : Label =
      new Label(300,middlePanelY + 60,"Number Of Snake :")
    var minSnake : Button = new Button(450,middlePanelY + 60, "-", 25,ButtonSettings.SMALL_BUTTON_HEIGHT)
    var numOfSnakeLabel : IntegerLabel =
      new IntegerLabel(480,middlePanelY + 60,numOfSnake.toString)
    var plusSnake : Button = new Button(515,middlePanelY + 60, "+", 25,ButtonSettings.SMALL_BUTTON_HEIGHT)

    /* Instansiasi komponen untuk mengontrol jumlah tangga */
    var numOfLadder : Int = 5;
    var numOfLadderTitleLabel : Label =
      new Label(300,middlePanelY + 100,"Number Of Ladder :")
    var minLadder : Button = new Button(450,middlePanelY + 100, "-", 25,ButtonSettings.SMALL_BUTTON_HEIGHT)
    var numOfLadderLabel : IntegerLabel =
      new IntegerLabel(480,middlePanelY + 100,numOfLadder.toString)
    var plusLadder : Button = new Button(515,middlePanelY + 100, "+", 25,ButtonSettings.SMALL_BUTTON_HEIGHT)

    /*
     * Mendefinisikan aksi untuk tombol plusPlayer
     */

    plusPlayer.setAction(new ButtonAction{
      /**
       * Method yang akan dipanggil saat tombol ditekan
       */
      override def buttonClicked: Unit = {
        if(numOfPlayer < GameRulesSettings.MAX_PLAYER){
          numOfPlayer += 1
          numOfPlayerLabel.increase
        }
      }
    })

    /*
     * Mendefinisikan aksi untuk tombol minPlayer
     */
    minPlayer.setAction(new ButtonAction {
      /**
       * Method yang akan dipanggil saat tombol ditekan
       */
      override def buttonClicked: Unit = {
        if(numOfPlayer > GameRulesSettings.MIN_PLAYER){
          numOfPlayer -= 1
          numOfPlayerLabel.decrease
        }
      }
    })

    /*
     * Mendefinisikan aksi untuk tombol plusSnake
     */
    plusSnake.setAction(new ButtonAction{
      /**
        * Method yang akan dipanggil saat tombol ditekan
        */
      override def buttonClicked: Unit = {
        if(numOfSnake < GameRulesSettings.MAX_SNAKE){
          numOfSnake += 1
          numOfSnakeLabel.increase
        }
      }
    })

    /*
     * Mendefinisikan aksi untuk tombol minSnake
     */
    minSnake.setAction(new ButtonAction {
      /**
        * Method yang akan dipanggil saat tombol ditekan
        */
      override def buttonClicked: Unit = {
        if(numOfSnake > GameRulesSettings.MIN_SNAKE){
          numOfSnake -= 1
          numOfSnakeLabel.decrease
        }
      }
    })

    /*
     * Mendefinisikan aksi untuk tombol plusLadder
     */
    plusLadder.setAction(new ButtonAction{
      /**
        * Method yang akan dipanggil saat tombol ditekan
        */
      override def buttonClicked: Unit = {
        if(numOfLadder < GameRulesSettings.MAX_SNAKE){
          numOfLadder += 1
          numOfLadderLabel.increase
        }
      }
    })

    /*
     * Mendefinisikan aksi untuk tombol minLadder
     */
    minLadder.setAction(new ButtonAction {
      /**
        * Method yang akan dipanggil saat tombol ditekan
        */
      override def buttonClicked: Unit = {
        if(numOfLadder > GameRulesSettings.MIN_SNAKE){
          numOfLadder -= 1
          numOfLadderLabel.decrease
        }
      }
    })

    /*
     * Mendefinisikan aksi untuk tombol playButton
     */
    playButton.setAction(new ButtonAction {
      /**
        * Method yang akan dipanggil saat tombol ditekan
        */
      override def buttonClicked: Unit = {
        var board : Board = new Board(30,30,10,10)
        /* Menginstansiasi game stage */
        var gameStage : GameStage = new GameStage(numOfPlayer, board)
        board.setGameStage(gameStage)
        board.populateSnake(numOfSnake)
        board.populateLadder(numOfLadder)

        GameStateManager.addState(new PlayState("PlayState", board))
        GameStateManager.setActiveState("PlayState")
      }
    })

    /* Menambah komponen-komponen background ke state */
    addComponentObject(background)
    addComponentObject(backgroundBlur)

    /* Menambah panel pada state */
    addComponentObject(middlePanel)

    /* Menambah komponen-komponen yang digunakan untuk membuat penambah dan
     * pengurang jumlah player ke state
     */
    addComponentObject(numOfPlayerTitleLabel)
    addComponentObject(numOfPlayerLabel)
    addComponentObject(plusPlayer)
    addComponentObject(minPlayer)

    /*
     * Menambah komponen-komponen yang digunakan untuk membuat penambah dan
     * pengurang jumlah snake ke state
     */
    addComponentObject(numOfSnakeTitleLabel)
    addComponentObject(numOfSnakeLabel)
    addComponentObject(plusSnake)
    addComponentObject(minSnake)

    /*
     * Menambah komponen-komponen yang digunakan untuk membuat penambah dan
     * pengurang jumlah ladder/tangga ke state
     */
    addComponentObject(numOfLadderTitleLabel)
    addComponentObject(numOfLadderLabel)
    addComponentObject(plusLadder)
    addComponentObject(minLadder)

    /*
     * Menambah komponen tombol play
     */
    addComponentObject(playButton)
  }
}
