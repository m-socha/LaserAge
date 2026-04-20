class Enemy(var x: Int, var y: Int, var width: Int = 40, var height: Int = 40) {
  def strength: Int = 2
  private var currentStrength: Int = strength

  def takeDamage(amount: Int): Unit = currentStrength -= amount
  def isDestroyed: Boolean = currentStrength <= 0

  private var direction: Int = 1
  private val speed = 2

  def move(): Unit = {
    x += direction * speed
    if (x <= 0 || x + width >= GameConfig.GAME_WIDTH) {
      direction *= -1
    }
  }
}