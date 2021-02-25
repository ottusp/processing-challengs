import processing.core.PApplet

fun main() {
    PApplet.main("MainProcessing")
}

class MainProcessing: PApplet() {

    private var cols = 0
    private var rows = 0
    private var w = 1200
    private var h = 800
    private val scl = 10f

    private var globalAngle = 0f
    private var zoom = 0f
    private var flying = 0f

    private lateinit var terrain: Array<Array<Float>>

    override fun settings() {
        size(600, 400, P3D)
    }

    override fun setup() {
        cols = (w / scl).toInt() + 1
        rows = (h / scl).toInt()

        var xoff = 0f
        terrain = Array(cols) { x ->
            xoff += 0.3f
            var yoff = 0f
            Array(rows) { y ->
                yoff += 0.3f
                map(noise(xoff*1f, yoff*1f), 0f, 1f, -50f, 50f)
            }
        }

        frameRate(60f)
    }

    override fun draw() {
        val minHeight = -100f
        val maxHeight = 100f

        background(0)
        noFill()

        flying += 0.05f
        var xoff = 0f
        repeat(cols) { x ->
            xoff += 0.05f
            var yoff = flying
            repeat(rows) { y ->
                yoff += 0.05f
                terrain[x][y] = map(noise(xoff*1f, yoff*1f), 0f, 1f, minHeight, maxHeight)
            }
        }

        translate(width/2f, height/2f)
        rotateX(globalAngle)
        translate(-w/2f, -h/2f)

        repeat(rows - 1) { y ->
            beginShape(TRIANGLE_STRIP)
            repeat(cols) { x ->
                val grey = map(terrain[x][y], minHeight, maxHeight, 150f, 255f)
                stroke(grey)
                vertex3D(zoom, x*scl, y*scl, terrain[x][y])
                vertex3D(zoom, x*scl, (y+1)*scl, terrain[x][y+1])
//                vertex(x*scl, y*scl, terrain[x][y])
//                vertex(x*scl, (y+1)*scl, terrain[x][(y+1) % rows])
            }
            endShape()
        }

        globalAngle = (mouseX/(width * 1f)) * PI
        zoom = (mouseY/(height * 1f)) * PI/3f

    }

    private fun vertex3D(angle: Float, x: Float, y: Float, z: Float) {
        val translatedY = h/2 - y
        val rotatedY = cos(angle) * translatedY + h/2

        val translatedX = w/2 - x
        val rotatedX = cos(angle) * translatedX + w/2
        vertex(rotatedX, rotatedY, z)
    }
}