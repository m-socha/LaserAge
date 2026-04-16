import java.awt.event.KeyEvent

class GameController(gameModel: GameModel) {
  def update(inputHandler: InputHandler): Unit = {
    handleInput(inputHandler)
    gameModel.updatePositions()
    gameModel.checkCollisions()
    gameModel.checkGameState()
  }

  private def handleInput(inputHandler: InputHandler): Unit = {
    // Position player based on mouse X coordinate
    val targetX = inputHandler.getMouseX
    val targetY = inputHandler.getMouseY
    gameModel.setPlayerPosition(targetX, targetY)

    // Fire bullet on left click
    if (inputHandler.consumeLeftClick()) {
      gameModel.firePlayerBullet()
    }
  }
}




