class PlayerTest extends munit.FunSuite:
  private def playerWithStrength(strength: Int): Player =
    val player = new Player()
    (1 until strength).foreach(_ => player.increaseStrength())
    player

  test("takeDamage decrements strength and triggers hit flash"):
    val startingStrength = 2
    val player = playerWithStrength(startingStrength)

    player.takeDamage()

    assertEquals(player.currentStrength, startingStrength - 1)
    assert(player.isHit)

  test("increaseStrength increments player strength"):
    val startingStrength = 2
    val player = playerWithStrength(startingStrength)

    player.increaseStrength()

    assertEquals(player.currentStrength, startingStrength + 1)

  test("isDestroyed becomes true when strength reaches zero or less"):
    val startingStrength = 1
    val player = playerWithStrength(startingStrength)

    player.takeDamage()

    assertEquals(player.currentStrength, 0)
    assert(player.isDestroyed)