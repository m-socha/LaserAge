import javax.swing._

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

    while (isRunning && !gameModel.gameOver && !gameModel.gameWon) {
      val startTime = System.currentTimeMillis()

      gameController.update(inputHandler)

      updateGameModel()

      canvas.render()

      val elapsedTime = System.currentTimeMillis() - startTime
      val sleepTime = (frameTime - elapsedTime).toLong
      if (sleepTime > 0) Thread.sleep(sleepTime)

      if (!frame.isDisplayable()) isRunning = false
    }

    cleanup()
  }

  private def updateGameModel(): Unit = {
    gameModel.updatePositions()
    gameModel.checkCollisions()
    gameModel.checkGameState()
  }

  private def init(): Unit = {
    canvas.setPreferredSize(new java.awt.Dimension(GameConfig.GAME_WIDTH, GameConfig.GAME_HEIGHT))
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    frame.add(canvas)
    frame.pack()
    frame.setResizable(false)
    frame.setLocationRelativeTo(null)
    frame.setVisible(true)

    canvas.createBufferStrategy(2)

    canvas.requestFocus()
    canvas.setIgnoreRepaint(true)
    frame.setIgnoreRepaint(true)

    SoundManager.preload("/explosion.wav")

    println("Game initialized")
  }

  private def cleanup(): Unit = {
    frame.dispose()
    println("Game closed")
  }
}
