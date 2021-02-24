import processing.core.PApplet

fun main() {
    PApplet.main("MainProcessing")
}

class MainProcessing: PApplet() {

    var cols = 0
    var rows = 0
    val scl = 20f

    override fun settings() {
        size(600, 400, P3D)
    }

    override fun setup() {
        cols = (width / scl).toInt()
        rows = (height / scl).toInt()
    }

    override fun draw() {
        background(0)
        stroke(255)
        noFill()

        translate(width/2f, height/2f)
        rotateX(PI/3)
        translate(-width/2f, -height/2f)

        repeat(rows) { y ->
            beginShape(TRIANGLE_STRIP)
            repeat(cols) { x ->
                vertex(x*scl, y*scl)
                vertex(x*scl, (y+1)*scl)
            }
            endShape()
        }
    }

    private fun vertex3d(x: Float, y: Float) {

        val angle = PI/3

        val newX = x * sin(angle) - x * cos(angle)
        val newY = y * cos(angle) - y * sin(angle)

        vertex(newX, newY)
    }
}