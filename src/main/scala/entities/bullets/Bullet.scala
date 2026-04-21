abstract class Bullet(var x: Int, var y: Int, val direction: Direction):
  def imagePath: String
  def soundPath: Option[String]
  def strength: Int
  def speed: Int
  def width: Int
  def height: Int

  def move(): Unit = y += direction.dy * speed
