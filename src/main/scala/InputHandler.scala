import java.awt.event._

class InputHandler {
  private var mouseX: Int = 0
  private var mouseY: Int = 0

  def handleMouseMoved(x: Int, y: Int): Unit = {
    mouseX = x
    mouseY = y
  }

  def getMouseX: Int = mouseX
  def getMouseY: Int = mouseY
}
