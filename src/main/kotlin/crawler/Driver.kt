package crawler

import org.jsoup.Jsoup
import java.net.URL

class Driver(private val rootUrl:URL, private val socketConnection: SocketConnection, private val urlExtractor: UrlExtractor, private val phoneNumberExtractor: PhoneNumberExtractor) {
    // Once you've completed the minimum requirements, take some time to impress us with extra features. Some ideas: parallelism, tests, obeying robots.txt, controlling crawl-depth, etc. Whatever shows off your strengths is what we want to see!

    private val crawledURLs = hashSetOf<String>()
    private val phoneNumbers = ArrayList<String>()

    fun collectPhoneNumbers(): List<String> {
        val urlSource = SocketConnection().getURLSource(rootUrl)
        val links = urlExtractor.getLinks(urlSource, rootUrl, rootUrl.path.isNotEmpty())


        findPhoneNumbers(links)

        return phoneNumbers
    }

    private fun findPhoneNumbers(links: Set<URL>) {
        links.map { it.toString() }.forEach { url ->
            crawledURLs.add(url)
            val urlSource1: String? = try {
                socketConnection.getURLSource(URL(url))
            } catch(e: Exception) {
                null
            }
            urlSource1?.let{phoneNumbers.addAll(phoneNumberExtractor.findPhoneNumbers(Jsoup.parse(it)))}
            val sublinks = urlSource1?.let { urlExtractor.getLinks(it, URL(url), rootUrl.path.isNotBlank()) }
            if(sublinks?.isNotEmpty() == true && !crawledURLs.contains(url)) {
                findPhoneNumbers(links)
            }


        }
    }

}