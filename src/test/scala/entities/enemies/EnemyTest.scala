class EnemyTest extends munit.FunSuite:
  private class DummyEnemy(startX: Int, startY: Int, hp: Int)
      extends Enemy(startX, startY, width = 10, height = 10):
    def imagePath: String = "dummy"
    def strength: Int = hp
    def move(): Unit = ()
    protected def makeBullet(x: Int, y: Int): Bullet = new BasicBullet(x, y, Direction.Down)

  test("takeDamage reduces effective health and isDestroyed flips at zero"):
    val enemy = new DummyEnemy(10, 20, hp = 3)

    enemy.takeDamage(1)
    assertEquals(enemy.isDestroyed, false)
    enemy.takeDamage(2)

    assertEquals(enemy.isDestroyed, true)