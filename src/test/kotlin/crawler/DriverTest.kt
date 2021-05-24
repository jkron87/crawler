package crawler

import org.junit.Assert.*
import org.junit.Test

class DriverTest{
    @Test
    fun phoneNumbers() {
        val collectPhoneNumbers = Driver("", SocketConnection(), UrlExtractor(), PhoneNumberExtractor()).collectPhoneNumbers()
    }
}