class GameModel {
  private val player = new Player()
  var enemies = scala.collection.mutable.ListBuffer[Enemy]()
  var bullets = scala.collection.mutable.ListBuffer[Bullet]()
  var score = 0
  var gameOver = false

  private val gameWidth = 800

  def setPlayerX(x: Int): Unit = {
    val centeredX = x - player.width / 2
    if (centeredX < 0) {
      player.setPlayerX(0)
    } else if (centeredX + player.width > gameWidth) {
      player.setPlayerX(gameWidth - player.width)
    } else {
      player.setPlayerX(centeredX)
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

