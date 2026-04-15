import javax.swing._
import java.awt._
import java.awt.event._

class GameCanvas(gameModel: GameModel, inputHandler: InputHandler) extends JPanel {
  private val renderer = new GameRenderer()

  this.setBackground(Color.BLACK)
  this.setFocusable(true)

  this.addKeyListener(new KeyListener {
    override def keyPressed(e: KeyEvent): Unit = {
      inputHandler.handleKeyPressed(e.getKeyCode)
    }

    override def keyReleased(e: KeyEvent): Unit = {
      inputHandler.handleKeyReleased(e.getKeyCode)
    }

    override def keyTyped(e: KeyEvent): Unit = {}
  })

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    renderer.render(g, gameModel)
  }
}
