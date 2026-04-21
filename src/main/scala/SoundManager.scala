import javax.sound.sampled._
import java.io.ByteArrayInputStream

object SoundManager {
  private case class AudioData(bytes: Array[Byte], format: AudioFormat)
  private val dataCache = scala.collection.mutable.Map[String, AudioData]()
  private val poolCache = scala.collection.mutable.Map[String, Array[Clip]]()
  private val PoolSize  = 8

  def preload(path: String): Unit =
    try
      val stream = AudioSystem.getAudioInputStream(new java.io.BufferedInputStream(getClass.getResourceAsStream(path)))
      val format = stream.getFormat
      val bytes  = stream.readAllBytes()
      stream.close()
      val data = AudioData(bytes, format)
      dataCache(path) = data
      poolCache(path) = Array.fill(PoolSize) {
        val ais  = new AudioInputStream(new ByteArrayInputStream(data.bytes), data.format, data.bytes.length / data.format.getFrameSize)
        val clip = AudioSystem.getClip()
        clip.open(ais)
        clip
      }
    catch case e: Exception => e.printStackTrace()

  def play(path: String): Unit =
    try
      val clips = poolCache(path)
      val clip  = clips.find(!_.isRunning).getOrElse(clips(0))
      clip.stop()
      clip.setFramePosition(0)
      clip.start()
    catch case e: Exception => e.printStackTrace()
}
