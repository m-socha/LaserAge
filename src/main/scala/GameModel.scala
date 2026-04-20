class GameModel {
  private val player = new Player()
  var enemies = scala.collection.mutable.ListBuffer[Enemy](
    Enemy(150, 100),
    Enemy(650, 100)
  )
  var bullets = scala.collection.mutable.ListBuffer[Bullet]()
  var score = 0
  var gameOver = false

  private val minPlayerY = 400

  def setPlayerPosition(x: Int, y: Int): Unit = {
    val centeredX = x - player.width / 2
    if (centeredX < 0) {
      player.setPlayerX(0)
    } else if (centeredX + player.width > GameConfig.GAME_WIDTH) {
      player.setPlayerX(GameConfig.GAME_WIDTH - player.width)
    } else {
      player.setPlayerX(centeredX)
    }

    if (y < minPlayerY) {
      player.setPlayerY(minPlayerY)
    } else if (y > GameConfig.GAME_HEIGHT - player.height) {
      player.setPlayerY(GameConfig.GAME_HEIGHT - player.height)
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
    // Update enemy positions
    enemies.foreach(_.move())

    // Update bullet positions
    bullets.foreach(_.move())

    // Remove off-screen bullets
    bullets = bullets.filter(_.y > 0)
  }

  private def overlaps(ax: Int, ay: Int, aw: Int, ah: Int,
                        bx: Int, by: Int, bw: Int, bh: Int): Boolean =
    ax < bx + bw && ax + aw > bx && ay < by + bh && ay + ah > by

  def checkCollisions(): Unit = {
    val destroyedEnemies = scala.collection.mutable.Set[Enemy]()
    val hitBullets = scala.collection.mutable.Set[Bullet]()

    // Bullet-enemy collisions
    for {
      bullet <- bullets
      enemy  <- enemies
      if overlaps(bullet.x, bullet.y, bullet.width, bullet.height,
                  enemy.x,  enemy.y,  enemy.width,  enemy.height)
    } {
      enemy.takeDamage(bullet.strength)
      hitBullets += bullet
      if (enemy.isDestroyed) {
        destroyedEnemies += enemy
        score += 1
      }
    }

    // Player-enemy collisions
    for {
      enemy <- enemies
      if overlaps(player.x, player.y, player.width, player.height,
                  enemy.x,  enemy.y,  enemy.width,  enemy.height)
    } {
      gameOver = true
    }

    enemies = enemies.filterNot(destroyedEnemies.contains)
    bullets = bullets.filterNot(hitBullets.contains)
  }

  def checkGameState(): Unit = {
    // TODO: Check win/lose conditions
  }
}


