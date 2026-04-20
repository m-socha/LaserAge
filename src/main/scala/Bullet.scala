class Bullet(var x: Int, var y: Int, val direction: Direction, val width: Int = 5, val height: Int = 10):
  def strength: Int = 1

  private val speed = 5

  def move(): Unit = y += direction.dy * speed
