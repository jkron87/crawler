package crawler

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import java.net.URL


class UrlExtractor {
    fun getLinks(htmlSourceCode: String, baseUrlString: String): Set<URL> {
        val url = URL(baseUrlString)
        val doc: Document = Jsoup.parse(htmlSourceCode)
        val set = doc.select("a").map { it.attr("href") }.toSet()

        val urls:HashSet<URL> = HashSet()
        set.forEach {
            if(!it.contains(url.path)) {
               urls.add(URL(url.protocol, url.host, url.path ?: "" + it))
            } else {
                urls.add(URL(url.protocol, url.host, it))
            }
        }
        return urls




    }
}