class BasicEnemy(startX: Int, startY: Int) extends Enemy(startX, startY, width = 40, height = 40) {
  def imagePath = "/assets/sprites/enemies/basic_enemy.png"
  def strength: Int = 2
  protected def makeBullet(x: Int, y: Int) = new BasicBullet(x - BasicBullet.Width / 2, y, Direction.Down)

  private var direction: Int = 1
  private val speed = 2

  def move(): Unit = {
    x += direction * speed
    if (x <= 0 || x + width >= GameConfig.GAME_WIDTH) {
      direction *= -1
    } else if (scala.util.Random.nextInt(180) == 0) {
      direction *= -1
    }
  }
}
