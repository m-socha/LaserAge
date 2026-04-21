import java.awt._
import java.awt.geom.Ellipse2D
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

  private val playedExplosions = scala.collection.mutable.Set[Explosion]()
  private val playedBullets    = scala.collection.mutable.Set[Bullet]()

  private val bg = loadImage("/assets/backgrounds/background.png")
  private val bgScale = math.max(GameConfig.GAME_WIDTH.toDouble / bg.getWidth, GameConfig.GAME_HEIGHT.toDouble / bg.getHeight)
  private val bgW = (bg.getWidth * bgScale).toInt
  private val bgH = (bg.getHeight * bgScale).toInt
  private val bgX = (GameConfig.GAME_WIDTH - bgW) / 2
  private val bgY = (GameConfig.GAME_HEIGHT - bgH) / 2

  def render(g: Graphics, gameModel: GameModel): Unit = {
    val g2d = g.asInstanceOf[Graphics2D]

    g2d.drawImage(bg, bgX, bgY, bgW, bgH, null)

    // Draw player
    g2d.drawImage(loadImage("/assets/sprites/player.png"), gameModel.playerX, gameModel.playerY, gameModel.playerWidth, gameModel.playerHeight, null)

    // Draw enemies
    for (enemy <- gameModel.enemies) {
      g2d.drawImage(loadImage(enemy.imagePath), enemy.x, enemy.y, enemy.width, enemy.height, null)
    }

    // Draw bullets
    for (bullet <- gameModel.bullets) {
      g2d.drawImage(loadImage(bullet.imagePath), bullet.x, bullet.y, bullet.width, bullet.height, null)
    }

    // Play bullet sounds for new bullets, clean up gone ones
    for (b <- gameModel.bullets if !playedBullets.contains(b)) {
      b.soundPath.foreach(SoundManager.play)
      playedBullets += b
    }
    playedBullets.filterInPlace(gameModel.bullets.contains)

    // Play explosion sounds for new explosions, clean up finished ones
    for (e <- gameModel.explosions if !playedExplosions.contains(e)) {
      SoundManager.play(Sounds.Explosion)
      playedExplosions += e
    }
    playedExplosions.filterInPlace(gameModel.explosions.contains)

    // Draw explosions
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    val oldComposite = g2d.getComposite
    for (e <- gameModel.explosions) {
      val p = e.progress
      val fade = 1.0f - p
      val cx = e.x.toFloat
      val cy = e.y.toFloat
      val fireR  = 8 + 22 * p
      val shockR = 10 + 32 * p

      // Shockwave ring
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade * 0.5f))
      g2d.setColor(new Color(255, 120, 0))
      g2d.setStroke(new BasicStroke(1.5f))
      g2d.draw(new Ellipse2D.Float(cx - shockR, cy - shockR, shockR * 2, shockR * 2))

      // Outer fireball — deep red/orange
      g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade * 0.9f))
      g2d.setPaint(new RadialGradientPaint(cx, cy, fireR,
        Array(0f, 0.4f, 0.75f, 1f),
        Array(new Color(255, 230, 80), new Color(255, 100, 0), new Color(180, 20, 0), new Color(100, 0, 0, 0))))
      g2d.fill(new Ellipse2D.Float(cx - fireR, cy - fireR, fireR * 2, fireR * 2))

      // Bright core flash (strongest at start)
      val coreR = 5 * (1.0f - p)
      if coreR > 0 then
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fade))
        g2d.setPaint(new RadialGradientPaint(cx, cy, coreR,
          Array(0f, 1f),
          Array(Color.WHITE, new Color(255, 200, 50, 0))))
        g2d.fill(new Ellipse2D.Float(cx - coreR, cy - coreR, coreR * 2, coreR * 2))
    }
    g2d.setComposite(oldComposite)
    g2d.setStroke(new BasicStroke(1f))

    // Draw HUD
    g2d.setColor(Color.WHITE)
    g2d.drawString(s"Wave: ${gameModel.currentWave}", 10, 20)
    g2d.drawString(s"Strength: ${gameModel.playerStrength}", 10, 35)
  }
}
