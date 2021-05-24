package crawler

import org.junit.Test
import java.net.URL

class DriverTest{
    @Test
    fun phoneNumbers() {
        val collectPhoneNumbers = Driver(URL("https://therecount.github.io/interview-materials/project-a/1.html"), SocketConnection(), UrlExtractor(), PhoneNumberExtractor()).collectPhoneNumbers()
    }
}