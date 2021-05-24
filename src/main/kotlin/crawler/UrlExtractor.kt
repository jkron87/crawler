package crawler

import org.apache.commons.io.FilenameUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.URL


class UrlExtractor {
    fun getLinks(htmlSourceCode: String, url: URL, containsPaths: Boolean): Set<URL> {
        val doc: Document = Jsoup.parse(htmlSourceCode)
        val set = doc.select("a").map { it.attr("href") }.toSet()

        val urls: HashSet<URL> = HashSet()

        set.forEach {
            val url1 = try {
                URL(it)
            } catch (e: Exception) {
                null
            }
            if (url1?.host != null && url1.host != url.host) {
                //noop
            } else {
                urls.add(URL(url.protocol, url.host, it))
                urls.add(URL(url.protocol, url.host, generateFilename(url) + it))
            }

        }
        return urls


    }

    private fun generateFilename(url: URL): String? {
        return if (FilenameUtils.getPath(url.path).startsWith("/")) {
            FilenameUtils.getPath(url.path)
        } else {
            "/" + FilenameUtils.getPath(url.path)
        }
    }
}