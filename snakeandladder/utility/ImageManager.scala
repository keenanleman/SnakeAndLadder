package snakeandladder.utility

import java.io.{FileNotFoundException, File}
import java.awt.Image
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

/**
 * Kelas yang berfungsi untuk memparse sebuah gambar dari file.
 * Created by ADMIN6 on 11/16/2015.
 */
class ImageManager extends AssetManager {
  /**
   * Fungsi untuk menarik gambar dari sebuah file.
   * @param filename lokasi dari file.
   * @return objek yang dihasilkan (di-parse).
   */  
  override def getAsset(filename: String): Object = {
    var file: File = new File(filename)
    return getAsset(file)
  }

  /**
   * Fungsi untuk menarik gambar dari sebuah file yang sudah ada.
   * @param file yang ingin di-parse.
   * @return gambar yang berhasil diparse.
   */
  override def getAsset(file: File): Object = {
    if (!file.exists()) {
      throw new FileNotFoundException("unable to locate file")
    }

    if (!file.canRead) {
      throw new IllegalAccessException("cannot access file")
    }

    var image : BufferedImage = null
    try {
      image = ImageIO.read(file)
    } catch {
      case e : Exception => e.printStackTrace()
    }

    return image
  }
}
