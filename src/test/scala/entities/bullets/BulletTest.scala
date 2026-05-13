class BulletTest extends munit.FunSuite:
  test("Bullet.move applies signed vertical speed from direction"):
    val upBullet = new BasicBullet(10, 100, Direction.Up)
    val downBullet = new BasicBullet(30, 100, Direction.Down)
    val upStartX = upBullet.x
    val upStartY = upBullet.y
    val downStartX = downBullet.x
    val downStartY = downBullet.y

    upBullet.move()
    downBullet.move()

    assertEquals(upBullet.x, upStartX)
    assertEquals(upBullet.y, upStartY - upBullet.speed)
    assertEquals(downBullet.x, downStartX)
    assertEquals(downBullet.y, downStartY + downBullet.speed)