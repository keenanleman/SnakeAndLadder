package snakeandladder.utility

import java.awt.{FontMetrics, Font, Color}
import java.awt.image.BufferedImage
import java.util.HashMap

/**
 * Objek untuk melakukan penarikan terhadap objek yang
 * sudah tersedia dalam bentuk file.
 *
 * Created by ADMIN6 on 11/16/2015.
 */
object AssetManager {
  /**
    * Merupakan tempat melakukan "mapping" dari nama dengan objek yang
    * ingin dijadikan aset.
    */
  private val colorAssets : HashMap[String, Color] = new HashMap
  private val imageAssets : HashMap[String, BufferedImage] = new HashMap
  private val fontAssets : HashMap[String, Font] = new HashMap
  private val fontMetricAssets : HashMap[String, FontMetrics] = new HashMap

  /**
    * Melakukan penambahan sebuah aset ke dalam manager ini.
    * @param registerName nama yang ingin dijadikan pedoman untuk objek {obj}.
    * @param color objek konkrit yang ingin dipanggil melalui {registerName}.
    * @return {true} bila manager berhasil menambahkan objek tersebut,
    *         {false} sebaliknya (contoh: bila objek telah ada dalam manager).
    */
  def registerAsset(registerName : String, color : Color): Boolean = {
    if (colorAssets.containsKey(registerName))
      return false
    colorAssets.put(registerName, color)
    return true
  }

  /**
    * Melakukan penambahan sebuah aset ke dalam manager ini.
    * @param registerName nama yang ingin dijadikan pedoman untuk objek {obj}.
    * @param font objek konkrit yang ingin dipanggil melalui {registerName}.
    * @return {true} bila manager berhasil menambahkan objek tersebut,
    *         {false} sebaliknya (contoh: bila objek telah ada dalam manager).
    */
  def registerAsset(registerName : String, font : Font,fontMetrics : FontMetrics): Boolean = {
    if (fontAssets.containsKey(registerName))
      return false
    fontAssets.put(registerName, font)
    fontMetricAssets.put(registerName, fontMetrics)
    return true
  }

  /**
    * Melakukan penambahan sebuah aset ke dalam manager ini.
    * @param registerName nama yang ingin dijadikan pedoman untuk objek {obj}.
    * @param image objek konkrit yang ingin dipanggil melalui {registerName}.
    * @return {true} bila manager berhasil menambahkan objek tersebut,
    *         {false} sebaliknya (contoh: bila objek telah ada dalam manager).
    */
  def registerAsset(registerName : String, image : BufferedImage): Boolean = {
    if (imageAssets.containsKey(registerName))
      return false
    imageAssets.put(registerName, image)
    return true
  }

  /**
    * Fungsi ini bertugas untuk mengembalikan objek yang ditampung dengan alias
    * yang diminta.
    * @param registerName alias dari objek yang ingin diambil.
    * @return objek yang ditampung dengan nama {registerName}. {null} dikembalikan
    *         bila objek tidak ada.
    */
  def getColor(registerName : String): Color = {
    if (!colorAssets.containsKey(registerName))
      return null
    return colorAssets.get(registerName)
  }

  /**
    * Fungsi ini bertugas untuk mengembalikan objek yang ditampung dengan alias
    * yang diminta.
    * @param registerName alias dari objek yang ingin diambil.
    * @return objek yang ditampung dengan nama {registerName}. {null} dikembalikan
    *         bila objek tidak ada.
    */
  def getFont(registerName : String): Font = {
    if (!fontAssets.containsKey(registerName))
      return null
    return fontAssets.get(registerName)
  }

  /**
    * Fungsi ini bertugas untuk mengembalikan objek yang ditampung dengan alias
    * yang diminta.
    * @param registerName alias dari objek yang ingin diambil.
    * @return objek yang ditampung dengan nama {registerName}. {null} dikembalikan
    *         bila objek tidak ada.
    */
  def getFontMetrics(registerName : String): FontMetrics = {
    if (!fontMetricAssets.containsKey(registerName))
      return null
    return fontMetricAssets.get(registerName)
  }

  /**
    * Fungsi ini bertugas untuk mengembalikan objek yang ditampung dengan alias
    * yang diminta.
    * @param registerName alias dari objek yang ingin diambil.
    * @return objek yang ditampung dengan nama {registerName}. {null} dikembalikan
    *         bila objek tidak ada.
    */
  def getImage(registerName : String): BufferedImage = {
    if (!imageAssets.containsKey(registerName))
      return null
    return imageAssets.get(registerName)
  }
}
