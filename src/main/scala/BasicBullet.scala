class BasicBullet(x: Int, y: Int, direction: Direction)
  extends Bullet(x, y, direction):
  val imagePath = "/basic_bullet.png"
  val strength = 1
  val speed    = 5
  val width    = 6
  val height   = 12
