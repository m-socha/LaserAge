class Enemy(var x: Int, var y: Int, var width: Int = 40, var height: Int = 40) {
  private var direction: Int = 1 // 1 for right, -1 for left
  private val speed = 2

  def move(): Unit = {
    x += direction * speed
    if (x <= 0 || x + width >= GameConfig.GAME_WIDTH) {
      direction *= -1
    }
  }
}