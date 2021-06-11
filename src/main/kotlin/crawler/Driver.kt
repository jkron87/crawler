package crawler

import org.jsoup.Jsoup
import java.net.URL

class Driver(private val rootUrl: URL, private val socketConnection: SocketConnection, private val urlExtractor: UrlExtractor, private val phoneNumberScraper: PhoneNumberScraper) {


    fun collectPhoneNumbers(): List<String> {
        val urlSource = try {
            SocketConnection().getURLSource(rootUrl)
        } catch (e: Exception) {
            null
        }

        val links = urlSource?.let { urlExtractor.getLinks(it, rootUrl) }

        return links?.let { recursivelyScrapeSublinks(arrayListOf(), hashSetOf(), it) } ?: listOf()
    }

    private tailrec fun recursivelyScrapeSublinks(phoneNumbers: ArrayList<String>, crawledURLs: HashSet<String>, links: Set<URL>): List<String> {
        links.map { it.toString() }.forEach { url ->
            crawledURLs.add(url)
            val urlSource: String? = try {
                socketConnection.getURLSource(URL(url))
            } catch (e: Exception) {
                null
            }
            urlSource?.let { phoneNumbers.addAll(phoneNumberScraper.findPhoneNumbers(Jsoup.parse(it))) }

            val sublinks = urlSource?.let { urlExtractor.getLinks(it, URL(url)) }

            if (sublinks?.isNotEmpty() == true && !crawledURLs.contains(url)) {
                return recursivelyScrapeSublinks(phoneNumbers, crawledURLs, links)
            }
        }

        return phoneNumbers
    }

}