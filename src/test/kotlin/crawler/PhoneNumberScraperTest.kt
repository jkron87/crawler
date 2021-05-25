package crawler

import com.google.i18n.phonenumbers.PhoneNumberUtil
import org.jsoup.Jsoup
import org.junit.Test
import kotlin.test.assertTrue

class PhoneNumberScraperTest {
    @Test
    fun `phoneNumberScraper finds phone numbers`() {
        val phoneNumberScraper = PhoneNumberScraper(PhoneNumberUtil.getInstance())
        val findPhoneNumbers = phoneNumberScraper.findPhoneNumbers(Jsoup.parse("<html>\n" +
                "  <body>\n" +
                "    <p>Front desk: 555-555-1234</p>\n" +
                "    <p>Back Office: (555)-555-2345</p>\n" +
                "  </body>\n" +
                "</html>\n"))
        assertTrue(findPhoneNumbers.contains("555-555-1234"))
        assertTrue(findPhoneNumbers.contains("(555)-555-2345"))
    }
}