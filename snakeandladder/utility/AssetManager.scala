package snakeandladder.utility

import java.util.HashMap

/**
 * Kelas abstrak untuk melakukan penarikan terhadap objek yang
 * sudah tersedia dalam bentuk file.
 *
 * Created by ADMIN6 on 11/16/2015.
 */
class AssetManager {
  /**
    * Merupakan tempat melakukan "mapping" dari nama dengan objek yang
    * ingin dijadikan aset.
    */
  private val assets : HashMap[String, Object] = new HashMap

  /**
    * Melakukan penambahan sebuah aset ke dalam manager ini.
    * @param registerName nama yang ingin dijadikan pedoman untuk objek {@code obj}.
    * @param obj objek konkrit yang ingin dipanggil melalui {@code registerName}.
    * @return {@code true} bila manager berhasil menambahkan objek tersebut,
    *         {@code false} sebaliknya (contoh: bila objek telah ada dalam manager).
    */
  def registerAsset(registerName : String, obj : Object): Boolean = {
    if (assets.containsKey(registerName))
      return false
    assets.put(registerName, obj)
    return true
  }

  /**
    * Fungsi ini bertugas untuk mengembalikan objek yang ditampung dengan alias
    * yang diminta.
    * @param registerName alias dari objek yang ingin diambil.
    * @return objek yang ditampung dengan nama {@code registerName}. {@code null} dikembalikan
    *         bila objek tidak ada.
    */
  def getAsset(registerName : String): Object = {
    if (!assets.containsKey(registerName))
      return null
    return assets.get(registerName)
  }

  /**
    * Merubah aset yang sudah ada di dalam aset manager ini.
    * @param registerName nama dari objek yang ingin dirubah isinya.
    * @param obj objek baru pengganti objek lama dengan nama {@code registerName}
    * @return {@code true} bila perubahan berhasil dilakukan.
    *         {@code false} jika gagal.
    */
  def modifyAsset(registerName : String, obj : Object): Boolean = {
    if (!assets.containsKey(registerName))
      return false
    assets.remove(registerName)
    assets.put(registerName, obj)
    return true
  }

  /**
    * Menghilangkan aset dengan nama tertentu dari manager ini.
    * @param registerName nama register dari objek yang ingin dihilangkan dari manager ini.
    */
  def removeAsset(registerName : String) {
    if (!assets.containsKey(registerName))
      return
    assets.remove(registerName)
  }
}
