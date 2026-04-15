class GameModel {
  private val player = new Player()
  var enemies = scala.collection.mutable.ListBuffer[Enemy]()
  var bullets = scala.collection.mutable.ListBuffer[Bullet]()
  var score = 0
  var gameOver = false

  private val gameWidth = 800
  private val gameHeight = 600
  private val minPlayerY = 400

  def setPlayerPosition(x: Int, y: Int): Unit = {
    val centeredX = x - player.width / 2
    if (centeredX < 0) {
      player.setPlayerX(0)
    } else if (centeredX + player.width > gameWidth) {
      player.setPlayerX(gameWidth - player.width)
    } else {
      player.setPlayerX(centeredX)
    }

    if (y < minPlayerY) {
      player.setPlayerY(minPlayerY)
    } else if (y > gameHeight - player.height) {
      player.setPlayerY(gameHeight - player.height)
    } else {
      player.setPlayerY(y)
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

