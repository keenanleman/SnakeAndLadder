package snakeandladder.gamestate

import snakeandladder.engine.{GameRulesSettings, ButtonSettings, GameDisplaySettings, GameEngineSettings}
import snakeandladder.gamecomponent._
import snakeandladder.gameobject.{Player, Dice, Board}
import snakeandladder.utility.AssetManager

class PrePlayState(stateTitle : String) extends GameState(stateTitle){
  /**
   * Method abstract untuk inisiasi GameState
   */
  override def init: Unit = {
    var background : Background = new Background(AssetManager.getImage("BackgroundWood"))
    var backgroundBlur : BackgroundColor = new BackgroundColor(AssetManager.getColor("BackgroundBlurColor"))

    var middlePanelHeight : Double = 200
    var middlePanelWidth : Double = GameDisplaySettings.WINDOW_WIDTH
    var middlePanelX : Double = 0
    var middlePanelY : Double = (GameDisplaySettings.WINDOW_HEIGHT/2) - (middlePanelHeight/2)
    var middlePanel : Panel = new Panel(middlePanelX,middlePanelY,middlePanelWidth,middlePanelHeight)


    var playButtonX = (middlePanelWidth / 2) - 50
    var playButtonY = middlePanelY + middlePanelHeight - ButtonSettings.BUTTON_HEIGHT - 16
    var playButton : Button = new Button(playButtonX,playButtonY,"Start!",100,ButtonSettings.BUTTON_HEIGHT)


    var numOfPlayer : Int = 2;
    var numOfPlayerTitleLabel : Label =
      new Label(300,middlePanelY + 20,"Number Of Player :")
    var minPlayer : Button = new Button(450,middlePanelY + 20, "-", 25,ButtonSettings.SMALL_BUTTON_HEIGHT)
    var numOfPlayerLabel : IntegerLabel =
      new IntegerLabel(480,middlePanelY + 20,numOfPlayer.toString)
    var plusPlayer : Button = new Button(515,middlePanelY + 20, "+", 25,ButtonSettings.SMALL_BUTTON_HEIGHT)


    var numOfSnake : Int = 5;
    var numOfSnakeTitleLabel : Label =
      new Label(300,middlePanelY + 60,"Number Of Snake :")
    var minSnake : Button = new Button(450,middlePanelY + 60, "-", 25,ButtonSettings.SMALL_BUTTON_HEIGHT)
    var numOfSnakeLabel : IntegerLabel =
      new IntegerLabel(480,middlePanelY + 60,numOfSnake.toString)
    var plusSnake : Button = new Button(515,middlePanelY + 60, "+", 25,ButtonSettings.SMALL_BUTTON_HEIGHT)

    var numOfLadder : Int = 5;
    var numOfLadderTitleLabel : Label =
      new Label(300,middlePanelY + 100,"Number Of Ladder :")
    var minLadder : Button = new Button(450,middlePanelY + 100, "-", 25,ButtonSettings.SMALL_BUTTON_HEIGHT)
    var numOfLadderLabel : IntegerLabel =
      new IntegerLabel(480,middlePanelY + 100,numOfLadder.toString)
    var plusLadder : Button = new Button(515,middlePanelY + 100, "+", 25,ButtonSettings.SMALL_BUTTON_HEIGHT)


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

    playButton.setAction(new ButtonAction {
      /**
        * Method yang akan dipanggil saat tombol ditekan
        */
      override def buttonClicked: Unit = {
        var board : Board = new Board(30,30,10,10)
        board.populateSnake(numOfSnake)
        //board.populateLadder(numOfLadder)
        for(i <- 0 until numOfPlayer) {
          board.addPlayer(new Player(board.getTileByNumber(1), board))
        }
        GameStateManager.addState(new PlayState("PlayState", board))
        GameStateManager.setActiveState("PlayState")
      }
    })

    var cb1 : CheckBoxButton = new CheckBoxButton(100,100, "Test",100,ButtonSettings.BUTTON_HEIGHT)
    cb1.setValue(GameEngineSettings.getAntialiasSettings)
    cb1.setAction(new ButtonAction{
      /**
        * Method yang akan dipanggil saat tombol ditekan
        */
      override def buttonClicked: Unit = {
        GameEngineSettings.setAntialiasing(cb1.getValue)
      }
    })


    addComponentObject(background)
    addComponentObject(backgroundBlur)

    addComponentObject(middlePanel)

    addComponentObject(numOfPlayerTitleLabel)
    addComponentObject(numOfPlayerLabel)
    addComponentObject(plusPlayer)
    addComponentObject(minPlayer)

    addComponentObject(numOfSnakeTitleLabel)
    addComponentObject(numOfSnakeLabel)
    addComponentObject(plusSnake)
    addComponentObject(minSnake)

    addComponentObject(numOfLadderTitleLabel)
    addComponentObject(numOfLadderLabel)
    addComponentObject(plusLadder)
    addComponentObject(minLadder)

    addComponentObject(playButton)
    addComponentObject(cb1)
  }
}
