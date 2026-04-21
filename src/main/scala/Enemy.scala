abstract class Enemy(var x: Int, var y: Int, val width: Int, val height: Int) {
  def imagePath: String
  def strength: Int
  private var currentStrength: Int = strength

  def takeDamage(amount: Int): Unit = currentStrength -= amount
  def isDestroyed: Boolean = currentStrength <= 0

  def move(): Unit

  protected def shoot(): Bullet =
    new BasicBullet(x + width / 2 - 2, y + height, Direction.Down)

  def maybeShoot(fire: Bullet => Unit): Unit =
    if scala.util.Random.nextInt(1000) < 5 then fire(shoot())
}
