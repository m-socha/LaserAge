import javax.swing._
import java.awt._
import java.awt.event._
import java.awt.image.BufferStrategy

class GameCanvas(gameModel: GameModel, inputHandler: InputHandler) extends Canvas {
  private val renderer = new GameRenderer()

  this.setFocusable(true)

  this.addMouseMotionListener(new MouseMotionListener {
    override def mouseMoved(e: MouseEvent): Unit = {
      inputHandler.handleMouseMoved(e.getX, e.getY)
    }

    override def mouseDragged(e: MouseEvent): Unit = {
      inputHandler.handleMouseMoved(e.getX, e.getY)
    }
  })

  this.addMouseListener(new MouseListener {
    override def mouseClicked(e: MouseEvent): Unit = {}

    override def mousePressed(e: MouseEvent): Unit = {
      if (e.getButton == MouseEvent.BUTTON1) {
        inputHandler.handleLeftClick()
      }
    }

    override def mouseReleased(e: MouseEvent): Unit = {}
    override def mouseEntered(e: MouseEvent): Unit = {}
    override def mouseExited(e: MouseEvent): Unit = {}
  })

  def render(): Unit = {
    // Get the strategy (created in your Game class after the frame is visible)
    val bs = this.getBufferStrategy

    if (bs == null) return // Safety check if strategy isn't initialized yet

    val g = bs.getDrawGraphics.asInstanceOf[Graphics2D]

    try {
      renderer.render(g, gameModel)
    } finally {
      // Release system resources
      g.dispose()
    }

    // Flip the buffer to the screen
    if (!bs.contentsLost()) {
      bs.show()
    }

    // Sync with display for smoother performance on some Linux/Unix systems
    Toolkit.getDefaultToolkit.sync()
  }
}

