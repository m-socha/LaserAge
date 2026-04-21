class Explosion(val x: Int, val y: Int) {
  val totalFrames = 5
  private val ticksPerFrame = 3
  private var tick = 0
  var currentFrame = 0

  def update(): Unit = {
    tick += 1
    if tick >= ticksPerFrame then
      tick = 0
      currentFrame += 1
  }

  def isDone: Boolean = currentFrame >= totalFrames
  def progress: Float = currentFrame.toFloat / totalFrames
}
