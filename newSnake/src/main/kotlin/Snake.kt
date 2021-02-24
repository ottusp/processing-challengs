import java.util.ArrayList

class Snake(private val matrix: Array<Array<Tile>>) {

    private var x = 0
    private var y = 0

    val snakeBody = ArrayList<Pair<Int, Int>>()

    private var xSpeed = 1
    private var ySpeed = 0

    private var isStopped = false

    init {
        snakeBody.add(Pair(x, y))
        matrix[x][y] = Tile.SNAKE
    }

    fun turnUp() {
        xSpeed = 0
        ySpeed = -1
    }

    fun turnDown() {
        xSpeed = 0
        ySpeed = 1
    }

    fun turnLeft() {
        xSpeed = -1
        ySpeed = 0
    }

    fun turnRight() {
        xSpeed = 1
        ySpeed = 0
    }

    fun move(): SnakeStatus {
        if(isStopped) return SnakeStatus.STOPPED

        x += xSpeed
        if(x > matrix.size - 1) x = 0
        else if(x < 0) x = matrix.size - 1

        y += ySpeed
        if(y > matrix[x].size - 1) y = 0
        else if(y < 0) y = matrix[x].size - 1

        val status: SnakeStatus = when(matrix[x][y]) {
            Tile.EMPTY -> SnakeStatus.OK
            Tile.SNAKE -> SnakeStatus.STOPPED
            Tile.FRUIT -> SnakeStatus.FRUITEATEN
        }

        snakeBody.add(Pair(x, y))
        matrix[x][y] = Tile.SNAKE

        if(status != SnakeStatus.FRUITEATEN) {
            val (tailX, tailY) = snakeBody.first()
            snakeBody.remove(Pair(tailX, tailY))
            matrix[tailX][tailY] = Tile.EMPTY
        }

        return status
    }

    fun stop() {
        xSpeed = 0
        ySpeed = 0

        isStopped = true
    }
}

enum class SnakeStatus {
    OK,
    STOPPED,
    FRUITEATEN,
    WIN
}