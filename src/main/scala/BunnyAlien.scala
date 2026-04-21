class BunnyAlien(startX: Int, startY: Int) extends Enemy(startX, startY, width = 40, height = 40) {
  def imagePath = "/bunny_alien.png"
  def strength: Int = 4

  private var dx = 1
  private var dy = 1
  private val speed = 4
  private val maxY = 350

  def move(): Unit = {
    x += dx * speed
    y += dy * speed
    if (x <= 0 || x + width >= GameConfig.GAME_WIDTH) dx *= -1
    else if (scala.util.Random.nextInt(180) == 0)      dx *= -1
    if (y <= 0 || y + height >= maxY)                 dy *= -1
    else if (scala.util.Random.nextInt(180) == 0)      dy *= -1
  }
}
