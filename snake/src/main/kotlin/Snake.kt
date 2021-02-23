import processing.core.PApplet

class Snake(private val applet: PApplet) {
    private var head: Pair<Int, Int>
    private lateinit var tail: Pair<Int, Int>
    private lateinit var body: Array<Int>

    init {
        applet.apply {
            head = Pair(width/2, height/2)
        }
    }
}