package crawler

import org.junit.Assert.*
import org.junit.Test

class PhoneNumberExtractorTest{
    @Test
    fun name() {
        val phoneNumberExtractor = PhoneNumberExtractor()
        val findPhoneNumbers = phoneNumberExtractor.findPhoneNumbers("<html>\n" +
                "  <body>\n" +
                "    <p>Front desk: 555-555-1234</p>\n" +
                "    <p>Back Office: 555-555-2345</p>\n" +
                "  </body>\n" +
                "</html>\n")
    }
}