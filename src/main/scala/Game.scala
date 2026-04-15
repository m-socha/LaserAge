import javax.swing._
import javax.swing.WindowConstants

class Game {
  private var isRunning = true
  private val targetFPS = 60
  private val frameTime = 1000.0 / targetFPS

  // Model
  private val gameModel = new GameModel()

  // Controller
  private val gameController = new GameController(gameModel)

  // Input
  private val inputHandler = new InputHandler()

  // View
  private val frame = new JFrame("Scala 2D Shooter")
  private val canvas = new GameCanvas(gameModel, inputHandler)

  def run(): Unit = {
    println("Game loop initialized")
    init()

    while (isRunning) {
      val startTime = System.currentTimeMillis()

      gameController.update(inputHandler)
      canvas.repaint()

      if (!frame.isDisplayable()) isRunning = false

      val elapsedTime = System.currentTimeMillis() - startTime
      val sleepTime = (frameTime - elapsedTime).toLong
      if (sleepTime > 0) Thread.sleep(sleepTime)
    }

    cleanup()
  }

  private def init(): Unit = {
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    frame.setSize(800, 600)
    frame.setResizable(false)
    frame.add(canvas)
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)
    canvas.requestFocus()
    println("Game initialized")
  }

  private def cleanup(): Unit = {
    frame.dispose()
    println("Game closed")
  }
}


