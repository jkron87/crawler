package crawler

import org.jsoup.Jsoup
import java.net.URL

class Driver(private val rootUrl: URL, private val socketConnection: SocketConnection, private val urlExtractor: UrlExtractor, private val phoneNumberScraper: PhoneNumberScraper) {

    private val crawledURLs = hashSetOf<String>()
    private val phoneNumbers = ArrayList<String>()

    fun collectPhoneNumbers(): List<String> {
        val urlSource = try {
            SocketConnection().getURLSource(rootUrl)
        } catch (e: Exception) {
            null
        }

        val links = urlSource?.let { urlExtractor.getLinks(it, rootUrl) }

        links?.let { recursivelyScrapeSublinks(it) }
        return phoneNumbers
    }

    private fun recursivelyScrapeSublinks(links: Set<URL>) {
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
                recursivelyScrapeSublinks(links)
            }
        }
    }

}