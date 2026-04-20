class Player(private var _x: Int = 400, private var _y: Int = 550, private val _width: Int = 50, private val _height: Int = 50) {
  def x: Int = _x
  def y: Int = _y
  def width: Int = _width
  def height: Int = _height

  private var _currentStrength: Int = 1
  def currentStrength: Int = _currentStrength
  def takeDamage(): Unit = _currentStrength -= 1
  def isDestroyed: Boolean = _currentStrength <= 0

  def setPlayerX(x: Int): Unit = _x = x
  def setPlayerY(y: Int): Unit = _y = y
}
