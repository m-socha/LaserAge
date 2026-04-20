abstract class Enemy(var x: Int, var y: Int, val width: Int, val height: Int) {
  def strength: Int
  private var currentStrength: Int = strength

  def takeDamage(amount: Int): Unit = currentStrength -= amount
  def isDestroyed: Boolean = currentStrength <= 0

  def move(): Unit
}
