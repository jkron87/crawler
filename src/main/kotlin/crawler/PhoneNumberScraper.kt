package crawler

import com.google.i18n.phonenumbers.PhoneNumberUtil
import org.jsoup.nodes.Document
import org.jsoup.select.Elements


open class PhoneNumberScraper(private val phoneNumberUtil: PhoneNumberUtil) {

    companion object {
        const val DEFAULT_REGION = "US"
    }

    fun findPhoneNumbers(sourceCode: Document):Set<String> {

        val allElements: Elements = sourceCode.allElements
        val elementsWithText: List<String> = allElements.filter { !it.text().isNullOrEmpty() }.map{it.text()}

        val phoneNumbers = mutableSetOf<String>()

        elementsWithText.forEach {
            val numbers = phoneNumberUtil.findNumbers(it, DEFAULT_REGION, PhoneNumberUtil.Leniency.POSSIBLE, 3).map { it.rawString() }
            phoneNumbers.addAll(numbers)
        }

        return phoneNumbers
    }
}