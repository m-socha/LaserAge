object Main {
  def main(args: Array[String]): Unit = {
    println("Scala 2D Shooter Game Starting...")

    val startWave = args.headOption.flatMap(_.toIntOption).map(_ - 1).getOrElse(0)
    val game = new Game(startWave)
    game.run()
  }
}
