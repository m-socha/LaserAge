class PowerupTest extends munit.FunSuite:
  test("move drifts horizontally while not falling"):
    val powerup = new Powerup(100, 200)
    val initialX = powerup.x
    val initialY = powerup.y

    powerup.move()

    assertEquals(powerup.x, initialX + 2)
    assertEquals(powerup.y, initialY)

  test("move reverses horizontal direction at side boundaries"):
    val powerup = new Powerup(GameConfig.GAME_WIDTH - 20, 150)
    val initialY = powerup.y

    powerup.move()
    val xAfterFirst = powerup.x
    val yAfterFirst = powerup.y
    powerup.move()

    assertEquals(xAfterFirst > powerup.x, true)
    assertEquals(powerup.x, GameConfig.GAME_WIDTH - powerup.width)
    assertEquals(yAfterFirst, initialY)
    assertEquals(powerup.y, initialY)

  test("startFalling switches motion mode"):
    val powerup = new Powerup(120, 220)
    val initialX = powerup.x
    val initialY = powerup.y

    powerup.startFalling()
    powerup.move()

    assertEquals(powerup.isFalling, true)
    assertEquals(powerup.x, initialX)
    assertEquals(powerup.y, initialY + 3)