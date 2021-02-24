import processing.core.PApplet
import processing.event.KeyEvent

fun main() {
    PApplet.main("MainProcessing")
}

class MainProcessing: PApplet() {

    private lateinit var matrix: Array<Array<Tile>>
    private val xSize = 20
    private val ySize = 20

    private lateinit var snake: Snake

    override fun settings() {
        size(640, 440)
    }

    override fun setup() {
        background(0)

        matrix = Array(30) {
            Array(20) {
                Tile.EMPTY
            }
        }

        snake = Snake(matrix)
        resetFruit()
    }

    override fun draw() {
        delay(70)
        val status = snake.move()

        if(status == SnakeStatus.STOPPED) {
            snake.stop()
            println("Game Over")
        } else if (status == SnakeStatus.FRUITEATEN) {
            resetFruit()
        }

        drawMatrix()
    }

    override fun keyPressed(event: KeyEvent?) {
        when(keyCode) {
            UP -> snake.turnUp()
            DOWN -> snake.turnDown()
            LEFT -> snake.turnLeft()
            RIGHT -> snake.turnRight()
        }
    }

    private fun drawMatrix() {
        for(x in matrix.indices) {
            for(y in matrix[x].indices) {
                when(matrix[x][y]) {
                    Tile.EMPTY -> fill(255)
                    Tile.FRUIT -> fill(200f, 40f, 130f)
                    Tile.SNAKE -> fill(130f, 240f, 80f)
                }

                rect(x*xSize*1f, y*ySize*1f, xSize*1f, ySize*1f)
            }
        }
    }

    private fun resetFruit() {
        val fruitLoc = pickFruitLocation()
        val (fruitX, fruitY) = fruitLoc.first

        if(fruitLoc.second == SnakeStatus.WIN) println("You won")
        else matrix[fruitX][fruitY] = Tile.FRUIT
    }

    private fun pickFruitLocation(): Pair<Pair<Int, Int>, SnakeStatus> {
        val fruitX = random(matrix.size.toFloat()).toInt()
        val fruitY = random(matrix[0].size.toFloat()).toInt()

        while(matrix[fruitX][fruitY] != Tile.EMPTY) {
            for(x in matrix.indices) {
                for(y in matrix[x].indices) {
                    println("x = $x, y = $y")
                    if(matrix[x][y] == Tile.EMPTY) {
                        return Pair(Pair(x, y), SnakeStatus.OK)
                    }
                }
            }
            return Pair(Pair(0, 0), SnakeStatus.WIN)
        }

        return Pair(Pair(fruitX, fruitY), SnakeStatus.OK)
    }
}

enum class Tile {
    SNAKE, FRUIT, EMPTY
}