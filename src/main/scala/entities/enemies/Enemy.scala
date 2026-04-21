abstract class Enemy(var x: Int, var y: Int, val width: Int, val height: Int) {
  def imagePath: String
  def strength: Int
  private var currentStrength: Int = strength

  def takeDamage(amount: Int): Unit = currentStrength -= amount
  def isDestroyed: Boolean = currentStrength <= 0

  def move(): Unit

  protected def makeBullet(x: Int, y: Int): Bullet

  protected def shoot(): Bullet = makeBullet(x + width / 2, y + height)

  def maybeShoot(): Option[Bullet] =
    if scala.util.Random.nextInt(1000) < 5 then Some(shoot()) else None
}
