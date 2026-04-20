class Bullet(var x: Int, var y: Int, var width: Int = 5, var height: Int = 10) {
  def strength: Int = 1

  def move(): Unit = {
    y -= 5
  }
}


