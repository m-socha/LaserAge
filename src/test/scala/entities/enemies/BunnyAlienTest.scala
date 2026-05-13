class BunnyAlienTest extends munit.FunSuite:
  private class ShootableBunnyAlien(startX: Int, startY: Int)
      extends BunnyAlien(startX, startY, BunnyAlienRandomnessPolicy.NeverReverse):
    def shootNow(): Bullet = shoot()

  test("move updates both X and Y while in free movement"):
    val bunny = new BunnyAlien(100, 100, BunnyAlienRandomnessPolicy.NeverReverse)
    val initialX = bunny.x
    val initialY = bunny.y

    bunny.move()

    assertEquals(bunny.x != initialX, true)
    assertEquals(bunny.y != initialY, true)

  test("move reverses horizontal direction at side boundaries"):
    val bunny = new BunnyAlien(GameConfig.GAME_WIDTH - 40, 100, BunnyAlienRandomnessPolicy.NeverReverse)

    bunny.move()
    val xAfterFirst = bunny.x
    val yAfterFirst = bunny.y
    bunny.move()

    assertEquals(bunny.x < xAfterFirst, true)
    assertEquals(bunny.y > yAfterFirst, true)

  test("move reverses vertical direction at top boundary"):
    val bunny = new BunnyAlien(100, -4, BunnyAlienRandomnessPolicy.NeverReverse)

    bunny.move()
    val xAfterFirst = bunny.x
    val yAfterFirst = bunny.y
    bunny.move()

    assertEquals(bunny.y < yAfterFirst, true)
    assertEquals(bunny.x > xAfterFirst, true)

  test("move reverses vertical direction at maxY boundary"):
    val bunny = new BunnyAlien(100, 310, BunnyAlienRandomnessPolicy.NeverReverse)

    bunny.move()
    val xAfterFirst = bunny.x
    val yAfterFirst = bunny.y
    bunny.move()

    assertEquals(bunny.y < yAfterFirst, true)
    assertEquals(bunny.x > xAfterFirst, true)

  test("shoot creates downward bullet from bunny center"):
    val bunny = new ShootableBunnyAlien(140, 90)

    val bullet = bunny.shootNow()

    assertEquals(bullet.isInstanceOf[BasicBullet], true)
    assertEquals(bullet.direction, Direction.Down)
    assertEquals(bullet.x, bunny.x + bunny.width / 2 - bullet.width / 2)
    assertEquals(bullet.y, bunny.y + bunny.height)