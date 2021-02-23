import processing.core.PApplet

fun main() {
    PApplet.main("MainProcessing")
}

class MainProcessing: PApplet() {

    private lateinit var stars: Array<Star>
    private var speed = 0f

    override fun settings() {
        size(680, 440)
    }

    override fun setup() {
        stars = Array(400) {
            Star(this)
        }
    }

    override fun draw() {
        background(0)
        translate((width/2).toFloat(), (height/2).toFloat())

        speed = map(mouseX.toFloat(), 0f, width.toFloat(), 1f, 50f)
        stars.forEach {
            it.update(speed)
            it.show()
        }
    }
}