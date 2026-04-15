import javax.swing._
import javax.swing.WindowConstants

class Game {
  private var isRunning = true
  private val targetFPS = 60
  private val frameTime = 1000.0 / targetFPS

  private val frame = new JFrame("Scala 2D Shooter")
  private val canvas = new GameCanvas()

  def run(): Unit = {
    println("Game loop initialized")
    init()

    while (isRunning) {
      val startTime = System.currentTimeMillis()

      handleInput()
      update()
      render()
      checkCollisions()
      checkGameState()

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
    println("Game initialized")
  }

  private def handleInput(): Unit = {
    // TODO: Handle user input (keyboard, mouse)
  }

  private def update(): Unit = {
    // TODO: Update game state (player position, enemies, bullets, etc.)
  }

  private def render(): Unit = {
    canvas.repaint()
  }

  private def checkCollisions(): Unit = {
    // TODO: Check player-enemy collisions
    // TODO: Check bullet-enemy collisions
    // TODO: Check enemy-projectile collisions
  }

  private def checkGameState(): Unit = {
    // TODO: Check win/lose conditions
    if (!frame.isDisplayable()) isRunning = false
  }

  private def cleanup(): Unit = {
    frame.dispose()
    println("Game closed")
  }
}

