class GameModel {
  private val player = new Player()
  var enemies = scala.collection.mutable.ListBuffer[Enemy]()
  var bullets = scala.collection.mutable.ListBuffer[Bullet]()
  var score = 0
  var gameOver = false

  private val gameWidth = 800

  def movePlayer(dx: Int): Unit = {
    val newPosition = player.x + dx
    if (player.x + dx <= 0) {
      player.moveBy(-player.x)
    } else if (player.x + player.width + dx >= gameWidth) {
      player.moveBy(gameWidth - player.width - player.x)
    } else {
      player.moveBy(dx)
    }
  }

  def playerX: Int = player.x
  def playerY: Int = player.y
  def playerWidth: Int = player.width
  def playerHeight: Int = player.height

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

