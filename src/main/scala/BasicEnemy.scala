class BasicEnemy(startX: Int, startY: Int) extends Enemy(startX, startY, width = 40, height = 40) {
  def strength: Int = 2

  private var direction: Int = 1
  private val speed = 2

  def move(): Unit = {
    x += direction * speed
    if (x <= 0 || x + width >= GameConfig.GAME_WIDTH) {
      direction *= -1
    }
  }
}
