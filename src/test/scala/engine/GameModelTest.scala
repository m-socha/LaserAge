import scala.collection.mutable.ListBuffer

class GameModelTest extends munit.FunSuite:
  private class TestEnemy(startX: Int, startY: Int, hp: Int)
      extends Enemy(startX, startY, width = 20, height = 20):
    def imagePath: String = "test-enemy"
    def strength: Int = hp
    def move(): Unit = ()
    protected def makeBullet(x: Int, y: Int): Bullet = new BasicBullet(x, y, Direction.Down)

  test("setPlayerPosition clamps player X at left boundary"):
    val model = new GameModel(0)
    val y = 420

    model.setPlayerPosition(-40, y)

    assertEquals(model.playerX, 0)
    assertEquals(model.playerY, y)

  test("setPlayerPosition clamps player X at right boundary"):
    val model = new GameModel(0)
    val y = 420

    model.setPlayerPosition(10_000, y)

    assertEquals(model.playerX, GameConfig.GAME_WIDTH - model.playerWidth)
    assertEquals(model.playerY, y)

  test("setPlayerPosition clamps player Y to MIN_PLAYER_Y"):
    val model = new GameModel(0)
    val inputX = 200
    val lowY = 100
    val expectedCenteredX = inputX - model.playerWidth / 2

    model.setPlayerPosition(inputX, lowY)

    assertEquals(model.playerX, expectedCenteredX)
    assertEquals(model.playerY, GameConfig.MIN_PLAYER_Y)

  test("setPlayerPosition keeps player Y when input is within allowed range"):
    val model = new GameModel(0)
    val inputX = 200
    val validY = GameConfig.MIN_PLAYER_Y + 10
    val expectedCenteredX = inputX - model.playerWidth / 2

    model.setPlayerPosition(inputX, validY)

    assertEquals(model.playerX, expectedCenteredX)
    assertEquals(model.playerY, validY)

  test("setPlayerPosition clamps player Y at bottom boundary"):
    val model = new GameModel(0)
    val inputX = 200
    val tooHighY = 10_000
    val expectedCenteredX = inputX - model.playerWidth / 2

    model.setPlayerPosition(inputX, tooHighY)

    assertEquals(model.playerX, expectedCenteredX)
    assertEquals(model.playerY, GameConfig.GAME_HEIGHT - model.playerHeight)

  test("updatePositions removes bullets outside vertical screen bounds"):
    val model = new GameModel(0)
    val topOut = new BasicBullet(10, 0, Direction.Up)
    val inRange = new BasicBullet(20, 100, Direction.Up)
    val bottomOut = new BasicBullet(30, GameConfig.GAME_HEIGHT, Direction.Down)
    model.bullets = ListBuffer(topOut, inRange, bottomOut)

    model.updatePositions()

    assertEquals(model.bullets.size, 1)
    assertEquals(model.bullets.head.x, inRange.x)
    assertEquals(model.bullets.head.y, inRange.y)

  test("updatePositions removes powerups once below screen"):
    val model = new GameModel(0)
    val fallingPowerup = new Powerup(100, GameConfig.GAME_HEIGHT)
    fallingPowerup.startFalling()
    model.powerups = ListBuffer(fallingPowerup)

    model.updatePositions()

    assertEquals(model.powerups.size, 0)

  test("checkCollisions applies non-fatal player-bullet damage to enemy"):
    val model = new GameModel(0)
    val enemy = new TestEnemy(100, 100, hp = 2)
    val bullet = new BasicBullet(enemy.x + 5, enemy.y + 5, Direction.Up)
    model.enemies = ListBuffer(enemy)
    model.bullets = ListBuffer(bullet)

    model.checkCollisions()

    assertEquals(model.enemies.size, 1)
    assertEquals(model.enemies.head.isDestroyed, false)
    assertEquals(model.bullets.size, 0)
    assertEquals(model.explosions.size, 1)

  test("checkCollisions applies fatal player-bullet damage to enemy and removes destroyed enemy"):
    val model = new GameModel(0)
    val enemy = new BasicEnemy(100, 100)
    val bullet1 = new BasicBullet(enemy.x + 5, enemy.y + 5, Direction.Up)
    val bullet2 = new BasicBullet(enemy.x + 10, enemy.y + 10, Direction.Up)
    model.enemies = ListBuffer(enemy)
    model.bullets = ListBuffer(bullet1, bullet2)

    model.checkCollisions()

    assertEquals(model.enemies.size, 0)
    assertEquals(model.bullets.size, 0)
    assertEquals(model.explosions.size, 2)

  test("checkCollisions starts powerup falling when hit by player bullet"):
    val model = new GameModel(0)
    val powerup = new Powerup(100, 100)
    val bullet = new BasicBullet(powerup.x + 2, powerup.y + 2, Direction.Up)
    model.powerups = ListBuffer(powerup)
    model.bullets = ListBuffer(bullet)

    assertEquals(model.powerups.head.isFalling, false)
    model.checkCollisions()

    assertEquals(model.powerups.head.isFalling, true)
    assertEquals(model.bullets.size, 0)

  test("checkCollisions grants strength and increments collected count when player touches falling powerup"):
    val model = new GameModel(0)
    model.setPlayerPosition(200, 420)
    val powerup = new Powerup(model.playerX, model.playerY)
    powerup.startFalling()
    val initialStrength = model.playerStrength
    model.powerups = ListBuffer(powerup)

    model.checkCollisions()

    assertEquals(model.playerStrength, initialStrength + 1)
    assertEquals(model.powerupsCollected, 1)
    assertEquals(model.powerups.size, 0)

  test("checkCollisions damages player on enemy bullet hit and sets gameOver when strength reaches zero"):
    val model = new GameModel(0)
    model.setPlayerPosition(200, 420)
    val enemyBullet = new BasicBullet(model.playerX + 1, model.playerY + 1, Direction.Down)
    model.bullets = ListBuffer(enemyBullet)

    model.checkCollisions()

    assertEquals(model.playerHit, true)
    assertEquals(model.bullets.size, 0)
    assertEquals(model.gameOver, true)

  test("checkGameState advances to next wave when both enemies and powerups are empty"):
    val model = new GameModel(2)
    model.enemies = ListBuffer.empty
    model.powerups = ListBuffer.empty

    model.checkGameState()

    // currentWave is 1-indexed; starting at waveIndex 2 (wave 3) advances to wave 4.
    assertEquals(model.currentWave, 4)
    assert(model.enemies.nonEmpty)

  test("checkGameState sets gameWon after clearing final wave"):
    val lastWaveIndex = Waves.all.size - 1
    val model = new GameModel(lastWaveIndex)
    model.enemies = ListBuffer.empty
    model.powerups = ListBuffer.empty

    model.checkGameState()

    assertEquals(model.gameWon, true)

  test("checkGameState does not advance when enemies remain"):
    val model = new GameModel(0)
    val initialWave = model.currentWave
    model.enemies = ListBuffer(new BasicEnemy(25, 25))
    model.powerups = ListBuffer.empty

    model.checkGameState()

    assertEquals(model.currentWave, initialWave)

  test("checkGameState does not advance when only a non-falling powerup remains"):
    val model = new GameModel(0)
    val initialWave = model.currentWave
    model.enemies = ListBuffer.empty
    model.powerups = ListBuffer(new Powerup(50, 50))

    model.checkGameState()

    assertEquals(model.currentWave, initialWave)

  test("checkGameState does not advance when only a falling powerup remains"):
    val model = new GameModel(0)
    val initialWave = model.currentWave
    val p = new Powerup(50, 50)
    p.startFalling()
    model.enemies = ListBuffer.empty
    model.powerups = ListBuffer(p)

    model.checkGameState()

    assertEquals(model.currentWave, initialWave)
