case class Wave(enemies: Seq[Enemy])

object Waves:
  val all: Seq[Wave] = Seq(
    Wave(Seq(
      new BasicEnemy( 60,  40),
      new BasicEnemy(180, 200),
      new BasicEnemy(300,  80)
    )),
    Wave(Seq(
      new BasicEnemy( 20,  30),
      new BasicEnemy(100, 250),
      new BasicEnemy(180,  90),
      new BasicEnemy(260, 300),
      new BasicEnemy(340,  50)
    )),
    Wave(Seq(
      new BasicEnemy( 20, 280),
      new BasicEnemy( 80,  50),
      new BasicEnemy(140, 200),
      new BasicEnemy(200,  30),
      new BasicEnemy(270, 260),
      new BasicEnemy(340, 120)
    )),
  )
