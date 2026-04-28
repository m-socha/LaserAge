class Powerup(var x: Int, var y: Int) {
  val width  = 20
  val height = 20

  private val driftSpeed = 2
  private val fallSpeed  = 3
  private var dx = driftSpeed
  private var falling = false

  def isFalling: Boolean = falling

  def startFalling(): Unit = falling = true

  def move(): Unit =
    if falling then
      y += fallSpeed
    else
      x += dx
      if x <= 0 || x + width >= GameConfig.GAME_WIDTH then dx = -dx
}
