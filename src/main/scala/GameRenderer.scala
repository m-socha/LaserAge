import java.awt._

class GameRenderer {
  def render(g: Graphics, gameModel: GameModel): Unit = {
    val g2d = g.asInstanceOf[Graphics2D]

    // Draw player
    g2d.setColor(Color.WHITE)
    g2d.fillRect(gameModel.player.x, gameModel.player.y, gameModel.player.width, gameModel.player.height)

    // Draw enemies
    g2d.setColor(Color.RED)
    for (enemy <- gameModel.enemies) {
      g2d.fillRect(enemy.x, enemy.y, enemy.width, enemy.height)
    }

    // Draw bullets
    g2d.setColor(Color.YELLOW)
    for (bullet <- gameModel.bullets) {
      g2d.fillRect(bullet.x, bullet.y, bullet.width, bullet.height)
    }

    // Draw score
    g2d.setColor(Color.WHITE)
    g2d.drawString(s"Score: ${gameModel.score}", 10, 20)
  }
}
