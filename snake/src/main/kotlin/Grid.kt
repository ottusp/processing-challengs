import processing.core.PApplet

class Grid(
    private val applet: PApplet,
    _xTiles: Int = 60,
    _yTiles: Int = 40
) {

    private var xTiles = 1
    private var yTiles = 1

    private var xSize = 1
    private var ySize = 1

    var matrix: Array<IntArray>

    init {
        xTiles = when {
            _xTiles < 1 -> 1
            _xTiles > applet.width -> applet.width
            else -> _xTiles
        }


        yTiles = when {
            _yTiles < 1 -> 1
            _yTiles > applet.height -> applet.height
            else -> _yTiles
        }

        xSize = applet.width/xTiles
        ySize = applet.height/yTiles

        matrix = Array(xTiles) { IntArray(yTiles) }
    }

    fun draw() {

        applet.apply {
            fill(255)
            strokeWeight(1f)

            repeat(xTiles) { x ->
                repeat(yTiles) { y ->
                    rect((x * xSize).toFloat(), (y * ySize).toFloat(), xSize.toFloat(), ySize.toFloat())
                }
            }
        }

    }
}
