class GameModel {
  private val player = new Player()
  private var waveIndex = 0
  var enemies = scala.collection.mutable.ListBuffer[Enemy](Waves.all(0).enemies*)
  var bullets = scala.collection.mutable.ListBuffer[Bullet]()
  var gameOver = false
  var gameWon = false

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

  def firePlayerBullet(): Unit = bullets += player.shoot()

  def currentWave: Int = waveIndex + 1
  def playerStrength: Int = player.currentStrength

  def playerX: Int = player.x
  def playerY: Int = player.y
  def playerWidth: Int = player.width
  def playerHeight: Int = player.height

  def updatePositions(): Unit = {
    // Update enemy positions
    enemies.foreach(_.move())
    enemies.foreach(_.maybeShoot().foreach(bullets += _))

    // Update bullet positions
    bullets.foreach(_.move())

    // Remove off-screen bullets
    bullets = bullets.filter(b => b.y > 0 && b.y < GameConfig.GAME_HEIGHT)
  }

  private def overlaps(ax: Int, ay: Int, aw: Int, ah: Int,
                        bx: Int, by: Int, bw: Int, bh: Int): Boolean =
    ax < bx + bw && ax + aw > bx && ay < by + bh && ay + ah > by

  def checkCollisions(): Unit = {
    val destroyedEnemies = scala.collection.mutable.Set[Enemy]()
    val hitBullets = scala.collection.mutable.Set[Bullet]()

    // Bullet-enemy collisions (player bullets only)
    for {
      bullet <- bullets if bullet.direction == Direction.Up
      enemy  <- enemies
      if overlaps(bullet.x, bullet.y, bullet.width, bullet.height,
                  enemy.x,  enemy.y,  enemy.width,  enemy.height)
    } {
      enemy.takeDamage(bullet.strength)
      hitBullets += bullet
      if (enemy.isDestroyed) {
        destroyedEnemies += enemy
      }
    }

    // Bullet-player collisions (enemy bullets only)
    for {
      bullet <- bullets if bullet.direction == Direction.Down
      if overlaps(bullet.x, bullet.y, bullet.width, bullet.height,
                  player.x, player.y, player.width, player.height)
    } {
      player.takeDamage()
      hitBullets += bullet
      if (player.isDestroyed) gameOver = true
    }

    enemies = enemies.filterNot(destroyedEnemies.contains)
    bullets = bullets.filterNot(hitBullets.contains)
  }

  def checkGameState(): Unit = {
    if enemies.isEmpty && !gameWon then
      waveIndex += 1
      if waveIndex >= Waves.all.size then
        gameWon = true
      else
        enemies = scala.collection.mutable.ListBuffer[Enemy](Waves.all(waveIndex).enemies*)
  }
}


