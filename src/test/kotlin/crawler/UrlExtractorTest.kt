package crawler

import org.junit.Test
import java.net.URL
import kotlin.test.assertTrue

class UrlExtractorTest {
    @Test
    fun `UrlExtractor should not include external domains`() {
        val links: Set<URL> = UrlExtractor().getLinks("<html>\n" +
                "<body><p>This is a page!</p>\n" +
                "<p><a href=\"2.html\">Page #2</a> - <a href=\"http://www.externaldomains.com\">Page #3</a></body>\n" +
                "</html>", URL("https://therecount.github.io/interview-materials/project-a/1.html"))

        assertTrue(!links.contains(URL("https://www.externaldomains.com")))

    }

    @Test
    fun `UrlExtractor should attach path from source url`() {
        val links: Set<URL> = UrlExtractor().getLinks("<html>\n" +
                "<body><p>This is a page!</p>\n" +
                "<p><a href=\"2.html\">Page #2</a> - <a href=\"http://www.externaldomains.com\">Page #3</a></body>\n" +
                "</html>", URL("https://therecount.github.io/interview-materials/project-a/1.html"))

        assertTrue(!links.contains(URL("https://therecount.github.io/interview-materials/project-a/1.html")))
    }
}