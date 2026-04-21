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

    // Draw background (cover: scale uniformly to fill, crop overflow)
    val bg = loadImage("/background.png")
    val scale = math.max(GameConfig.GAME_WIDTH.toDouble / bg.getWidth, GameConfig.GAME_HEIGHT.toDouble / bg.getHeight)
    val drawW = (bg.getWidth * scale).toInt
    val drawH = (bg.getHeight * scale).toInt
    val drawX = (GameConfig.GAME_WIDTH - drawW) / 2
    val drawY = (GameConfig.GAME_HEIGHT - drawH) / 2
    g2d.drawImage(bg, drawX, drawY, drawW, drawH, null)

    // Draw player
    g2d.drawImage(loadImage("/player.png"), gameModel.playerX, gameModel.playerY, gameModel.playerWidth, gameModel.playerHeight, null)

    // Draw enemies
    for (enemy <- gameModel.enemies) {
      g2d.drawImage(loadImage(enemy.imagePath), enemy.x, enemy.y, enemy.width, enemy.height, null)
    }

    // Draw bullets
    for (bullet <- gameModel.bullets) {
      g2d.drawImage(loadImage(bullet.imagePath), bullet.x, bullet.y, bullet.width, bullet.height, null)
    }

    // Draw HUD
    g2d.setColor(Color.WHITE)
    g2d.drawString(s"Wave: ${gameModel.currentWave}", 10, 20)
    g2d.drawString(s"Strength: ${gameModel.playerStrength}", 10, 35)
  }
}
