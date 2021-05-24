package crawler

import org.junit.Test
import java.net.URL

class SocketConnectionTest{
    @Test
    fun name() {
        val urlSource = SocketConnection().getURLSource(URL("https://therecount.github.io/interview-materials/project-a/1.html"))

    }
}