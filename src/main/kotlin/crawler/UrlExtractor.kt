package crawler

import org.apache.commons.io.FilenameUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.net.URL


class UrlExtractor {
    fun getLinks(htmlSourceCode: String, url: URL): Set<URL> {
        val doc: Document = Jsoup.parse(htmlSourceCode)
        val set = doc.select("a").map { it.attr("href") }.toSet()

        val urls: HashSet<URL> = HashSet()

        set.forEach {
            val scrapedUrl = try {
                URL(it)
            } catch (e: Exception) {
                null
            }

            if (scrapedUrl?.host != null && scrapedUrl.host != url.host) {
                //noop (we don't include other domains)
            } else {
                urls.add(URL(url.protocol, url.host, determineAddPrefixSlash(it)))

                //handle case where url contains paths
                if (!url.toString().contains(FilenameUtils.getPath(it)) || FilenameUtils.getPath(it).isEmpty()) {
                    urls.add(URL(url.protocol, url.host, determineAddPrefixSlash(FilenameUtils.getPath(url.path) + it)))
                }
            }

        }

        return urls

    }

    private fun determineAddPrefixSlash(path: String): String {
        return if (path.startsWith("/")) {
            path
        } else {
            "/$path"
        }
    }
}