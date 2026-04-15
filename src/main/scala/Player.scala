class Player(private var _x: Int = 400, private var _y: Int = 550, private val _width: Int = 50, private val _height: Int = 50) {
  def x: Int = _x
  def y: Int = _y
  def width: Int = _width
  def height: Int = _height

  def setPlayerX(x: Int): Unit = {
    _x = x
  }

  def setPlayerY(y: Int): Unit = {
    _y = y
  }
}
