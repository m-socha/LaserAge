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

  private val bg = loadImage("/background.png")
  private val bgScale = math.max(GameConfig.GAME_WIDTH.toDouble / bg.getWidth, GameConfig.GAME_HEIGHT.toDouble / bg.getHeight)
  private val bgW = (bg.getWidth * bgScale).toInt
  private val bgH = (bg.getHeight * bgScale).toInt
  private val bgX = (GameConfig.GAME_WIDTH - bgW) / 2
  private val bgY = (GameConfig.GAME_HEIGHT - bgH) / 2

  def render(g: Graphics, gameModel: GameModel): Unit = {
    val g2d = g.asInstanceOf[Graphics2D]

    g2d.drawImage(bg, bgX, bgY, bgW, bgH, null)

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
