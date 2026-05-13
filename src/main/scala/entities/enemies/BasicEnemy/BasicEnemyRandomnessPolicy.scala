trait BasicEnemyRandomnessPolicy {
  def shouldRandomlyReverseXDirection(): Boolean
}

object BasicEnemyRandomnessPolicy {
  final class RandomFrequencyPolicy(randomFrequency: Int) extends BasicEnemyRandomnessPolicy {
    require(randomFrequency > 0, "randomFrequency must be greater than 0")

    override def shouldRandomlyReverseXDirection(): Boolean =
      scala.util.Random.nextInt(randomFrequency) == 0
  }

  object NeverReverse extends BasicEnemyRandomnessPolicy {
    override def shouldRandomlyReverseXDirection(): Boolean = false
  }

  val Default: BasicEnemyRandomnessPolicy = new RandomFrequencyPolicy(180)
}