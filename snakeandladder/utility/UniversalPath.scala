package snakeandladder.utility

import java.io.File

/**
 * Kelas untuk mengkonversi path pada *nix OS ke Win* OS dan sebaliknya
 * @param uPath path yang ingin di konversi
 */
class UniversalPath(uPath : String) {
  override def toString : String = uPath.replaceAll("/",File.separator).replaceAll("\\\\",File.separator)
}
