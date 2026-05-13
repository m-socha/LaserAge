class BasicEnemyTest extends munit.FunSuite:
  private class ShootableBasicEnemy(startX: Int, startY: Int)
      extends BasicEnemy(startX, startY, BasicEnemyRandomnessPolicy.NeverReverse):
    def shootNow(): Bullet = shoot()

  test("move changes X only (no vertical movement)"):
    val enemy = new BasicEnemy(100, 120, BasicEnemyRandomnessPolicy.NeverReverse)
    val initialX = enemy.x
    val initialY = enemy.y

    enemy.move()

    assertEquals(enemy.x != initialX, true)
    assertEquals(enemy.y, initialY)

  test("move reverses direction on horizontal boundary"):
    val enemy = new BasicEnemy(GameConfig.GAME_WIDTH - 40, 100, BasicEnemyRandomnessPolicy.NeverReverse)
    val initialY = enemy.y

    enemy.move()
    val xAfterFirst = enemy.x
    val yAfterFirst = enemy.y
    enemy.move()

    assertEquals(enemy.x < xAfterFirst, true)
    assertEquals(yAfterFirst, initialY)
    assertEquals(enemy.y, initialY)

  test("shoot creates downward basic bullet centered on enemy"):
    val enemy = new ShootableBasicEnemy(150, 80)

    val bullet = enemy.shootNow()

    assertEquals(bullet.isInstanceOf[BasicBullet], true)
    assertEquals(bullet.direction, Direction.Down)
    assertEquals(bullet.x, enemy.x + enemy.width / 2 - bullet.width / 2)
    assertEquals(bullet.y, enemy.y + enemy.height)