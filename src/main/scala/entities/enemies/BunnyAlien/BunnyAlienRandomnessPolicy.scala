trait BunnyAlienRandomnessPolicy {
  def shouldRandomlyReverseXDirection(): Boolean
  def shouldRandomlyReverseYDirection(): Boolean
}

object BunnyAlienRandomnessPolicy {
  final class RandomFrequenciesPolicy(horizontalFrequency: Int, verticalFrequency: Int)
      extends BunnyAlienRandomnessPolicy {
    require(horizontalFrequency > 0, "horizontalFrequency must be greater than 0")
    require(verticalFrequency > 0, "verticalFrequency must be greater than 0")

    override def shouldRandomlyReverseXDirection(): Boolean =
      scala.util.Random.nextInt(horizontalFrequency) == 0

    override def shouldRandomlyReverseYDirection(): Boolean =
      scala.util.Random.nextInt(verticalFrequency) == 0
  }

  object NeverReverse extends BunnyAlienRandomnessPolicy {
    override def shouldRandomlyReverseXDirection(): Boolean = false
    override def shouldRandomlyReverseYDirection(): Boolean = false
  }

  val Default: BunnyAlienRandomnessPolicy =
    new RandomFrequenciesPolicy(horizontalFrequency = 180, verticalFrequency = 180)
}