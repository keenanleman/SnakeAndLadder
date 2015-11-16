package snakeandladder.utility

import java.io.File

class UniversalPath(uPath : String) {
  override def toString : String = uPath.replaceAll("/",File.separator).replaceAll("\\\\",File.separator)
}
