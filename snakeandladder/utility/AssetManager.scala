package snakeandladder.utility

import java.io.File

/**
 * Kelas abstrak untuk melakukan penarikan terhadap objek yang
 * sudah tersedia dalam bentuk file.
 */
abstract class AssetManager {
  /**
   * Fungsi untuk menarik objek tertentu
   * @param filename lokasi dari file.
   * @return objek yang dihasilkan (di-parse).
   */  
  def getAsset(filename: String): Object

  /**
   * Fungsi untuk menarik objek tertentu dari file.
   * @param file yang ingin di-parse.
   * @return objek yang berhasil diparse.
   */
  def getAsset(file : File): Object
}
