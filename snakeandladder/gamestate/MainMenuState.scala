package snakeandladder.gamestate

import snakeandladder.engine.{GameRulesSettings, ButtonSettings, GameDisplaySettings, GameEngineSettings,GameEngine}
import snakeandladder.gamecomponent._
import snakeandladder.gameobject.{Player, Dice, Board}
import snakeandladder.utility.AssetManager

/**
 * Kelas yang menjadi main menu dari game
 * @param stateTitle Judul/Nama dari state
 */
class MainMenuState(stateTitle : String, gameEngine : GameEngine) extends GameState(stateTitle){
  /**
   * Method abstract(overrided untuk MainMenuState) untuk inisiasi SettingsState
   */
  override def init: Unit = {
    /* Instansiasi komponen pembentuk background pada state */
    var background : Background = new Background(AssetManager.getImage("MainMenuBackground"))

    /* Instansiasi komponen pembentuk panel */
    var middlePanelHeight : Double = 200
    var middlePanelWidth : Double = GameDisplaySettings.WINDOW_WIDTH
    var middlePanelX : Double = 0
    var middlePanelY : Double = (GameDisplaySettings.WINDOW_HEIGHT/2) - (middlePanelHeight/2) + 100
    var middlePanel : Panel = new Panel(middlePanelX,middlePanelY,middlePanelWidth,middlePanelHeight)

    /* Menginstansiasi tombol 'Play' */
    var playGameBtn : Button = new Button(350,middlePanelY + 20, "Play", 100, ButtonSettings.BUTTON_HEIGHT)
    /* Menginstansiasi tombol 'Settings' */
    var settingsBtn : Button = new Button(350,middlePanelY + 60, "Settings", 100, ButtonSettings.BUTTON_HEIGHT)
    /* Menginstansiasi tombol 'About' */
    var aboutBtn : Button = new Button(350,middlePanelY + 100, "About", 100, ButtonSettings.BUTTON_HEIGHT)
    /* Menginstansiasi tombol 'Quit' */
    var quitGameBtn : Button = new Button(350,middlePanelY + 140, "Quit", 100, ButtonSettings.BUTTON_HEIGHT)

    /* Mengeset aksi untuk tombol 'Play' */
    playGameBtn.setAction(new ButtonAction {
      /**
       * Method yang akan dipanggil saat tombol ditekan
       */
      override def buttonClicked: Unit = {
        GameStateManager.setActiveState("PrePlayState")
      }
    })

    /* Mengeset aksi untuk tombol 'Settings' */
    settingsBtn.setAction(new ButtonAction {
      /**
       * Method yang akan dipanggil saat tombol ditekan
       */
      override def buttonClicked: Unit = {
        GameStateManager.setActiveState("SettingsState")
      }
    })

    /* Mengeset aksi untuk tombol 'About' */
    aboutBtn.setAction(new ButtonAction {
      /**
       * Method yang akan dipanggil saat tombol ditekan
       */
      override def buttonClicked: Unit = {
        GameStateManager.setActiveState("AboutState")
      }
    })

    /* Mengeset aksi untuk tombol 'About' */
    quitGameBtn.setAction(new ButtonAction {
      /**
       * Method yang akan dipanggil saat tombol ditekan
       */
      override def buttonClicked: Unit = {
        gameEngine.stopMainThread
      }
    })

    /* Menambah background dan background blur */
    addComponentObject(background)

    /* Menambah panel pada state */
    addComponentObject(middlePanel)

    /* Menambah tombol-tombol ke state */
    addComponentObject(playGameBtn)
    addComponentObject(settingsBtn)
    addComponentObject(aboutBtn)
    addComponentObject(quitGameBtn)
  }
}
