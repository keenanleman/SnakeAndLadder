package snakeandladder.gamestate

import snakeandladder.engine.{GameRulesSettings, ButtonSettings, GameDisplaySettings, GameEngineSettings}
import snakeandladder.gamecomponent._
import snakeandladder.gameobject.{Player, Dice, Board}
import snakeandladder.utility.AssetManager

/**
 * Kelas yang menjadi inteface untuk user mengubah
 * pengaturan game
 * @param stateTitle Judul/Nama dari state
 */
class SettingsState(stateTitle : String) extends GameState(stateTitle){
  /**
   * Method abstract(override untuk SettingsState) untuk inisiasi SettingsState
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
    
    /* Instansiasi komponen AntialiasCheckBox */
    var antialiasLabel : Label =
      new Label(300,middlePanelY + 60,"Use Antialisasing :")
    var antialiasCheckBox : CheckBoxButton = new CheckBoxButton(450,middlePanelY + 65, "Use Antialias",100,ButtonSettings.BUTTON_HEIGHT)
    antialiasCheckBox.setValue(GameEngineSettings.getAntialiasSettings)


    /* Instansiasi komponen untuk pengaturan fps */
    var currentFPS : Int = GameEngineSettings.getDefaultFPS
    var fpsLabel : Label =
      new Label(300,middlePanelY + 20,"Frame Per Seconds :")
    var minFPS : Button = new Button(450,middlePanelY + 20, "-", 25,ButtonSettings.SMALL_BUTTON_HEIGHT)
    var numOfFPSLabel : IntegerLabel =
      new IntegerLabel(480,middlePanelY + 20,currentFPS.toString)
    var plusFPS : Button = new Button(515,middlePanelY + 20, "+", 25,ButtonSettings.SMALL_BUTTON_HEIGHT)

    /* Menginstansiasi tombol 'Main Menu' */
    var exitToMainMenu : Button = new Button(300,middlePanelY + 100, "Main Menu", 100, ButtonSettings.BUTTON_HEIGHT)

    /* Mengeset aksi untuk checkbox pengaturan antialias */
    antialiasCheckBox.setAction(new ButtonAction{
      /**
        * Method yang akan dipanggil saat tombol ditekan
        */
      override def buttonClicked: Unit = {
        GameEngineSettings.setAntialiasing(antialiasCheckBox.getValue)
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

    /* Mengeset aksi untuk tombol 'plusFPS' */
    plusFPS.setAction(new ButtonAction {
      /**
       * Method yang akan dipanggil saat tombol ditekan
       */
      override def buttonClicked: Unit = {
        if(currentFPS < 60){
          GameEngineSettings.setDefaultFPS(currentFPS + 1)
          currentFPS = GameEngineSettings.getDefaultFPS
          numOfFPSLabel.increase
        }
      }
    })

    /* Mengeset aksi untuk tombol 'minFPS' */
    minFPS.setAction(new ButtonAction {
      /**
       * Method yang akan dipanggil saat tombol ditekan
       */
      override def buttonClicked: Unit = {
        if(currentFPS > 30){
          GameEngineSettings.setDefaultFPS(currentFPS - 1)
          currentFPS = GameEngineSettings.getDefaultFPS
          numOfFPSLabel.decrease
        }
      }
    })

    /* Mengambah background dan background blur ke state */
    addComponentObject(background)
    addComponentObject(backgroundBlur)

    /* Menambah panel pada state */
    addComponentObject(middlePanel)

    /* Menambah antialiasCheckBox pada state */
    addComponentObject(antialiasCheckBox)
    addComponentObject(antialiasLabel)

    /* Mebambah komponen untuk pengaturan fps pada state */
    addComponentObject(fpsLabel)
    addComponentObject(minFPS)
    addComponentObject(numOfFPSLabel)
    addComponentObject(plusFPS)

    /* Mengambah tombol 'Main Menu' ke state */
    addComponentObject(exitToMainMenu)
  }
}
