case class Wave(enemies: Seq[Enemy])

object Waves:
  val all: Seq[Wave] = Seq(
    Wave(Seq(
      new BasicEnemy(150,  40),
      new BasicEnemy(400, 200),
      new BasicEnemy(650,  80)
    )),
    Wave(Seq(
      new BasicEnemy(100,  30),
      new BasicEnemy(250, 250),
      new BasicEnemy(400,  90),
      new BasicEnemy(550, 320),
      new BasicEnemy(700,  50)
    )),
    Wave(Seq(
      new BasicEnemy( 80, 300),
      new BasicEnemy(220,  50),
      new BasicEnemy(360, 200),
      new BasicEnemy(500,  30),
      new BasicEnemy(640, 280),
      new BasicEnemy(750, 120)
    )),
  )
