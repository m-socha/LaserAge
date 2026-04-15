class GameModel {
  var player = new Player()
  var enemies = scala.collection.mutable.ListBuffer[Enemy]()
  var bullets = scala.collection.mutable.ListBuffer[Bullet]()
  var score = 0
  var gameOver = false

  def updatePositions(): Unit = {
    // TODO: Update enemy positions
    // TODO: Update bullet positions
  }

  def checkCollisions(): Unit = {
    // TODO: Check player-enemy collisions
    // TODO: Check bullet-enemy collisions
  }

  def checkGameState(): Unit = {
    // TODO: Check win/lose conditions
  }
}

