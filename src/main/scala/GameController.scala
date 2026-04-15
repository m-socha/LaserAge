import java.awt.event.KeyEvent

class GameController(gameModel: GameModel) {
  def update(inputHandler: InputHandler): Unit = {
    handleInput(inputHandler)
    gameModel.updatePositions()
    gameModel.checkCollisions()
    gameModel.checkGameState()
  }

  private def handleInput(inputHandler: InputHandler): Unit = {
    // Apply input to player movement through the model API.
    if (inputHandler.isKeyPressed(KeyEvent.VK_LEFT)) {
      gameModel.movePlayer(-5)
    }
    if (inputHandler.isKeyPressed(KeyEvent.VK_RIGHT)) {
      gameModel.movePlayer(5)
    }
    if (inputHandler.isKeyPressed(KeyEvent.VK_SPACE)) {
      // TODO: Fire bullet
    }
  }
}



