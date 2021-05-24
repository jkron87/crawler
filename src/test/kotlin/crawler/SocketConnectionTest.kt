package crawler

import org.junit.Assert.*
import org.junit.Test

class SocketConnectionTest{
    @Test
    fun name() {
        val urlSource = SocketConnection().getURLSource("https://therecount.github.io/interview-materials/project-a/1.html")

    }
}