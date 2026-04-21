object BasicBullet:
  val Width  = 6
  val Height = 12

class BasicBullet(x: Int, y: Int, direction: Direction)
  extends Bullet(x, y, direction):
  val imagePath = "/basic_bullet.png"
  val soundPath = Some(Sounds.BasicBullet)
  val strength = 1
  val speed    = 5
  val width    = BasicBullet.Width
  val height   = BasicBullet.Height
