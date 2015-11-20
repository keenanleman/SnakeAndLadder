package snakeandladder.gamestate

import snakeandladder.engine.{GameRulesSettings, ButtonSettings, GameDisplaySettings, GameEngineSettings}
import snakeandladder.gamecomponent._
import snakeandladder.gameobject.{Player, Dice, Board}
import snakeandladder.utility.AssetManager

/**
 * Kelas yang menampilkan authors game
 *
 * @param stateTitle Judul/Nama dari state
 */
class AboutState(stateTitle : String) extends GameState(stateTitle){
  /**
   * Method abstract(override untuk PrePlayState) untuk inisiasi SettingsState
   */
  override def init: Unit = {
    /* Instansiasi komponen pembentuk background pada state */
    var background : Background = new Background(AssetManager.getImage("BackgroundWood"))
    var backgroundBlur : BackgroundColor = new BackgroundColor(AssetManager.getColor("BackgroundBlurColor"))

    /* Instansiasi komponen pembentuk panel */
    var middlePanelHeight : Double = 355
    var middlePanelWidth : Double = GameDisplaySettings.WINDOW_WIDTH
    var middlePanelX : Double = 0
    var middlePanelY : Double = (GameDisplaySettings.WINDOW_HEIGHT/2) - (middlePanelHeight/2)
    var middlePanel : Panel = new Panel(middlePanelX,middlePanelY,middlePanelWidth,middlePanelHeight)

    /* Instansiasi komponen AntialiasCheckBox */
    var authorLabel1 : Label =
      new Label(300,middlePanelY + 20,"Keenan Leman | 2014730041")
    var authorLabel2 : Label =
      new Label(300,middlePanelY + 60,"Barsya Prastoro | 2014730042")
    var authorLabel3 : Label =
      new Label(300,middlePanelY + 100,"Mirza Lazuardi | 201473")
    var authorLabel4 : Label =
      new Label(300,middlePanelY + 140,"Samuel Lusandi | 2014730001")
    var authorLabel5 : Label =
      new Label(300,middlePanelY + 180,"Louise Marcho | 2014730002")
    var authorLabel6 : Label =
      new Label(300,middlePanelY + 220,"Krisogonus F Rendra | 201473")

    /* Menginstansiasi tombol 'Main Menu' */
    var exitToMainMenu : Button = new Button(300,middlePanelY + 300, "Main Menu", 100, ButtonSettings.BUTTON_HEIGHT)

    /* Mengeset aksi untuk tombol 'Main Menu' */
    exitToMainMenu.setAction(new ButtonAction {
      /**
       * Method yang akan dipanggil saat tombol ditekan
       */
      override def buttonClicked: Unit = {
        GameStateManager.setActiveState("MainMenuState")
      }
    })

    /* Mengambah background dan background blur ke state */
    addComponentObject(background)
    addComponentObject(backgroundBlur)

    /* Menambah panel pada state */
    addComponentObject(middlePanel)

    /* Menambah label-label untuk nama-nama author ke state */
    addComponentObject(authorLabel1)
    addComponentObject(authorLabel2)
    addComponentObject(authorLabel3)
    addComponentObject(authorLabel4)
    addComponentObject(authorLabel5)
    addComponentObject(authorLabel6)

    /* Menambah tombol 'Main Menu' ke state */
    addComponentObject(exitToMainMenu)
  }
}
