import processing.core.PApplet.map
import processing.core.PApplet

class Star(private val applet: PApplet) {

    private var x = 0f
    private var y = 0f
    private var z = 0f

    private var pz = 0f

    init {
        resetPosition()
    }


    private fun resetPosition() {
        applet.apply {
            x = random(-width / 2f, width / 2f)
            y = random(-height / 2f, height / 2f)
            z = random(width.toFloat())
            pz = z
        }
    }

    fun update(speed: Float = 5f) {
        pz = z
        z -= speed

        if(z < 1) {
            resetPosition()
        }
    }

    fun show() {

        applet.apply {
            val sx = map(x/z, 0f, 1f, 0f, width.toFloat())
            val sy = map(y/z, 0f, 1f, 0f, height.toFloat())

            val psx = map(x/pz, 0f, 1f, 0f, width.toFloat())
            val psy = map(y/pz, 0f, 1f, 0f, height.toFloat())

            val size = map(z, 0f, width.toFloat(), 5f, 0f)

            strokeWeight(size)
            stroke(255)

            line(sx, sy, psx, psy)
        }
    }

}