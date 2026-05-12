class InputHandlerTest extends munit.FunSuite:
  test("handleMouseMoved stores latest cursor position"):
    val input = new InputHandler()

    input.handleMouseMoved(123, 456)

    assertEquals(input.getMouseX, 123)
    assertEquals(input.getMouseY, 456)

  test("consumeLeftClick returns true once then resets"):
    val input = new InputHandler()
    input.handleLeftClick()

    val first = input.consumeLeftClick()
    val second = input.consumeLeftClick()

    assertEquals(first, true)
    assertEquals(second, false)