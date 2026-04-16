import javax.swing._
import java.awt._
import java.awt.event._

class GameCanvas(gameModel: GameModel, inputHandler: InputHandler) extends JPanel {
  private val renderer = new GameRenderer()

  this.setBackground(Color.BLACK)
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

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    renderer.render(g, gameModel)
  }
}

