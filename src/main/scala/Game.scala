class Game {
  private var isRunning = true
  private val targetFPS = 60
  private val frameTime = 1000.0 / targetFPS

  def run(): Unit = {
    println("Game loop initialized")
    init()

    while (isRunning) {
      val startTime = System.currentTimeMillis()

      // TODO: Handle user input (keyboard, mouse)
      handleInput()

      // TODO: Update game state (player position, enemies, bullets, etc.)
      update()

      // TODO: Render/draw everything
      render()

      // TODO: Check for collisions
      checkCollisions()

      // TODO: Check win/lose conditions
      checkGameState()

      // Cap frame rate
      val elapsedTime = System.currentTimeMillis() - startTime
      val sleepTime = (frameTime - elapsedTime).toLong
      if (sleepTime > 0) Thread.sleep(sleepTime)
    }

    cleanup()
  }

  private def init(): Unit = {
    // TODO: Initialize game window
    // TODO: Initialize player
    // TODO: Initialize enemies
    // TODO: Initialize game state (score, lives, etc.)
    println("Game initialized")
  }

  private def handleInput(): Unit = {
    // TODO: Read keyboard input
    // TODO: Update player direction/action
    // TODO: Check for quit command
  }

  private def update(): Unit = {
    // TODO: Update player position and state
    // TODO: Update enemy positions and behavior
    // TODO: Update bullet positions
    // TODO: Update animations and effects
  }

  private def render(): Unit = {
    // TODO: Clear screen
    // TODO: Draw player
    // TODO: Draw enemies
    // TODO: Draw bullets
    // TODO: Draw UI (score, lives, etc.)
  }

  private def checkCollisions(): Unit = {
    // TODO: Check player-enemy collisions
    // TODO: Check bullet-enemy collisions
    // TODO: Check enemy-projectile collisions
  }

  private def checkGameState(): Unit = {
    // TODO: Check if player is dead
    // TODO: Check if all enemies are defeated
    // TODO: Check for level completion
    // TODO: Set isRunning = false if game over
  }

  private def cleanup(): Unit = {
    // TODO: Close game window
    // TODO: Save high scores
    // TODO: Release resources
    println("Game closed")
  }
}
