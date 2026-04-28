class Player(private var _x: Int = 400, private var _y: Int = 550, private val _width: Int = 50, private val _height: Int = 50) {
  def x: Int = _x
  def y: Int = _y
  def width: Int = _width
  def height: Int = _height

  private var _currentStrength: Int = 1
  def currentStrength: Int = _currentStrength
  def isDestroyed: Boolean = _currentStrength <= 0

  private var _flashTicks = 0
  def isHit: Boolean = _flashTicks > 0
  def tickFlash(): Unit = if _flashTicks > 0 then _flashTicks -= 1

  def takeDamage(): Unit =
    _currentStrength -= 1
    _flashTicks = 30

  def increaseStrength(): Unit = _currentStrength += 1

  def shoot(): Seq[Bullet] =
    def centered(offsetX: Int = 0): Bullet =
      val bullet = new BasicBullet(0, _y - 10, Direction.Up)
      bullet.x = _x + _width / 2 - bullet.width / 2 + offsetX
      bullet
    _currentStrength match
      case 1 => Seq(centered())
      case _ => Seq(centered()) // TODO: implement higher strength firing patterns

  def setPlayerX(x: Int): Unit = _x = x
  def setPlayerY(y: Int): Unit = _y = y
}
