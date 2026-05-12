class WavesTest extends munit.FunSuite:
  test("Each wave's enemy spawn positions are inside map bounds"):
    Waves.all.zipWithIndex.foreach { case (wave, waveIndex) =>
      wave.enemies.zipWithIndex.foreach { case (enemy, enemyIndex) =>
        assert(
          enemy.x >= 0,
          s"Wave ${waveIndex + 1}, enemy ${enemyIndex + 1} x was ${enemy.x}"
        )
        assert(
          enemy.x + enemy.width <= GameConfig.GAME_WIDTH,
          s"Wave ${waveIndex + 1}, enemy ${enemyIndex + 1} x+width was ${enemy.x + enemy.width}"
        )
        assert(
          enemy.y >= 0,
          s"Wave ${waveIndex + 1}, enemy ${enemyIndex + 1} y was ${enemy.y}"
        )
        assert(
          enemy.y + enemy.height <= GameConfig.GAME_HEIGHT,
          s"Wave ${waveIndex + 1}, enemy ${enemyIndex + 1} y+height was ${enemy.y + enemy.height}"
        )
      }
    }