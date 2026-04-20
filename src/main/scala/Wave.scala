case class Wave(enemies: Seq[Enemy])

object Waves:
  val all: Seq[Wave] = Seq(
    Wave(Seq(
      new BasicEnemy(150, 100),
      new BasicEnemy(400, 100),
      new BasicEnemy(650, 100)
    )),
    Wave(Seq(
      new BasicEnemy(100, 80),
      new BasicEnemy(250, 80),
      new BasicEnemy(400, 80),
      new BasicEnemy(550, 80),
      new BasicEnemy(700, 80)
    ))
  )
