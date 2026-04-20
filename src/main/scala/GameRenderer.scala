import java.awt._
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class GameRenderer {
  private val imageCache = scala.collection.mutable.Map[String, BufferedImage]()

  private def loadImage(path: String): BufferedImage = {
    imageCache.getOrElseUpdate(path, {
      val raw = ImageIO.read(getClass.getResourceAsStream(path))
      val img = new BufferedImage(raw.getWidth, raw.getHeight, BufferedImage.TYPE_INT_ARGB)
      val g = img.createGraphics()
      g.drawImage(raw, 0, 0, null)
      g.dispose()
      img
    })
  }

  def render(g: Graphics, gameModel: GameModel): Unit = {
    val g2d = g.asInstanceOf[Graphics2D]

    // Draw player
    g2d.setColor(Color.WHITE)
    g2d.fillRect(gameModel.playerX, gameModel.playerY, gameModel.playerWidth, gameModel.playerHeight)

    // Draw enemies
    for (enemy <- gameModel.enemies) {
      g2d.drawImage(loadImage(enemy.imagePath), enemy.x, enemy.y, enemy.width, enemy.height, null)
    }

    // Draw bullets
    g2d.setColor(Color.YELLOW)
    for (bullet <- gameModel.bullets) {
      g2d.fillRect(bullet.x, bullet.y, bullet.width, bullet.height)
    }

    // Draw HUD
    g2d.setColor(Color.WHITE)
    g2d.drawString(s"Wave: ${gameModel.currentWave}", 10, 20)
    g2d.drawString(s"Strength: ${gameModel.playerStrength}", 10, 35)
  }
}
