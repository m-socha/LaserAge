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

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    renderer.render(g, gameModel)
  }
}
