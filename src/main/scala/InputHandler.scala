import java.awt.event._

class InputHandler {
  private var keys = scala.collection.mutable.Set[Int]()

  def handleKeyPressed(keyCode: Int): Unit = {
    keys.add(keyCode)
  }

  def handleKeyReleased(keyCode: Int): Unit = {
    keys.remove(keyCode)
  }

  def isKeyPressed(keyCode: Int): Boolean = {
    keys.contains(keyCode)
  }
}
