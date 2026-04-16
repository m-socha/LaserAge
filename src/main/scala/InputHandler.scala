import java.awt.event._

class InputHandler {
  private var mouseX: Int = 0
  private var mouseY: Int = 0
  private var leftClickPressed: Boolean = false

  def handleMouseMoved(x: Int, y: Int): Unit = {
    mouseX = x
    mouseY = y
  }

  def handleLeftClick(): Unit = {
    leftClickPressed = true
  }

  def getMouseX: Int = mouseX
  def getMouseY: Int = mouseY

  def consumeLeftClick(): Boolean = {
    val wasPressed = leftClickPressed
    leftClickPressed = false
    wasPressed
  }
}
