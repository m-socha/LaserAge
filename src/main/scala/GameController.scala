import java.awt.event.KeyEvent

class GameController(gameModel: GameModel) {
  def update(inputHandler: InputHandler): Unit = {
    handleInput(inputHandler)
    gameModel.updatePositions()
    gameModel.checkCollisions()
    gameModel.checkGameState()
  }

  private def handleInput(inputHandler: InputHandler): Unit = {
    // Apply input to player movement
    if (inputHandler.isKeyPressed(KeyEvent.VK_LEFT)) {
      gameModel.player.x -= 5
    }
    if (inputHandler.isKeyPressed(KeyEvent.VK_RIGHT)) {
      gameModel.player.x += 5
    }
    if (inputHandler.isKeyPressed(KeyEvent.VK_SPACE)) {
      // TODO: Fire bullet
    }
  }
}



