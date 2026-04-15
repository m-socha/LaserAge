import javax.swing._
import java.awt._

class GameCanvas extends JPanel {
  this.setBackground(Color.BLACK)

  override def paintComponent(g: Graphics): Unit = {
    super.paintComponent(g)
    g.setColor(Color.WHITE)
    g.fillRect(350, 250, 100, 100)
  }
}
