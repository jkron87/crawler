package crawler

import org.junit.Test

class UrlExtractorTest{
    @Test
    fun name() {
        val links = UrlExtractor().getLinks("<html>\n" +
                "<body><p>This is a page!</p>\n" +
                "<p><a href=\"2.html\">Page #2</a> - <a href=\"/interview-materials/project-a/3.html\">Page #3</a></body>\n" +
                "</html>", "https://therecount.github.io/interview-materials/project-a/")

    }
}