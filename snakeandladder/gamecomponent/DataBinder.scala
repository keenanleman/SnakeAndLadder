package snakeandladder.gamecomponent

import snakeandladder.gameobject.GameObjectUpdate

/**
 * Interface yang memberikan kemampuan kepada objek 
 * untuk melakukan 'data-binding'
 */
trait DataBinder extends GameObjectUpdate{
  /**
   * Aksi yang dilakukan saat update
   */
  private var dataBindAction : DataBindAction = new DataBindAction{
    override def updateData : Unit = {
      //Empty Method
    }
  }

  /**
   * Mengeset aksi pada objek DataBinder
   * @param dataBindAction objek yang menyediakan aksi
   */
  def setData(dataBindAction : DataBindAction) : Unit = {
    this.dataBindAction = dataBindAction
  }

  /**
   * Method override dari GameObjectUpdate
   * yang akan terus dipanggil oleh GameEngine
   */
  override def update : Unit = {
    dataBindAction.updateData
  }
}
