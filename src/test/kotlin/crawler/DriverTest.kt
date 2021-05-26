package crawler

import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.net.URL

class DriverTest {
    private val mockedPhoneNumberScraper: PhoneNumberScraper = Mockito.mock(PhoneNumberScraper::class.java)
    private val mockedSocketConnection: SocketConnection = Mockito.mock(SocketConnection::class.java)
    private val urlExtractor = UrlExtractor()

    @Before
    fun setUp() {
        val sourceCode = "<html>\n" +
                "<body><p>This is a page!</p>\n" +
                "<p><a href=\"2.html\">Page #2</a> - <a href=\"http://www.externaldomains.com\">Page #3</a></body>\n" +
                "</html>"

        Mockito.`when`(mockedSocketConnection.getURLSource(URL("https://www.colectivocoffee.com"))).thenReturn(sourceCode)
        Mockito.`when`(mockedSocketConnection.getURLSource(URL("http://www.externaldomains.com"))).thenThrow(RuntimeException())
    }

    @Test
    fun `Driver can handle exception in socket connection`() {

        val driver = Driver(URL("https://www.colectivocoffee.com"), mockedSocketConnection, urlExtractor, mockedPhoneNumberScraper)
        val collectedPhoneNumbers = driver.collectPhoneNumbers()

        assertEquals(0, collectedPhoneNumbers.size)
    }
}