import processing.core.PApplet

fun main() {
    PApplet.main("MainProcessing")
}

class MainProcessing: PApplet() {

    private lateinit var grid: Grid

    override fun settings() {
        size(640, 400)
    }

    override fun setup() {
        background(0)
        grid = Grid(this)
    }

    override fun draw() {
        grid.draw()
    }
}