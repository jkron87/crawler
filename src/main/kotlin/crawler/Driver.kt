package crawler

class Driver(private val rootUrl:String, private val socketConnection: SocketConnection, private val urlExtractor: UrlExtractor, private val phoneNumberExtractor: PhoneNumberExtractor) {

    fun collectPhoneNumbers(): List<String> {
        val urlSource = SocketConnection().getURLSource("https://therecount.github.io/interview-materials/project-a/1.html")


        val links = UrlExtractor().getLinks(urlSource, "https://therecount.github.io/interview-materials/project-a/")

        val phoneNumbers = ArrayList<String>()

        val stringLinks = links.map { it.toString() }.forEach{
            val urlSource1 = socketConnection.getURLSource(it)
            phoneNumbers.addAll(phoneNumberExtractor.findPhoneNumbers(urlSource1))

        }
        return phoneNumbers
    }

}