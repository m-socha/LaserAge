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

  def firePlayerBullet(): Unit = {
    val bulletX = player.x + player.width / 2 - 2 // Center bullet on player
    val bulletY = player.y - 10 // Spawn above player
    bullets += Bullet(bulletX, bulletY)
  }

  def playerX: Int = player.x
  def playerY: Int = player.y
  def playerWidth: Int = player.width
  def playerHeight: Int = player.height

  def updatePositions(): Unit = {
    // Update bullet positions (move upward)
    bullets.foreach(bullet => bullet.y -= 5)
    // Remove off-screen bullets
    bullets = bullets.filter(_.y > 0)
  }

  def checkCollisions(): Unit = {
    // TODO: Check player-enemy collisions
    // TODO: Check bullet-enemy collisions
  }

  def checkGameState(): Unit = {
    // TODO: Check win/lose conditions
  }
}


