package crawler

import org.jsoup.Jsoup
import org.junit.Test

class PhoneNumberExtractorTest{
    @Test
    fun name() {
        val phoneNumberExtractor = PhoneNumberExtractor()
        val findPhoneNumbers = phoneNumberExtractor.findPhoneNumbers(Jsoup.parse("<html>\n" +
                "  <body>\n" +
                "    <p>Front desk: 555-555-1234</p>\n" +
                "    <p>Back Office: 555-555-2345</p>\n" +
                "  </body>\n" +
                "</html>\n"))
    }
}