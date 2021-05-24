package crawler

import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class PhoneNumberExtractor {
    companion object{
    }

    fun findPhoneNumbers(sourceCode: Document):Set<String> {
        val regex = Regex("\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})")
        val allElements: Elements = sourceCode.allElements
        val elementsWithText: List<String> = allElements.filter { !it.text().isNullOrEmpty() }.map{it.text()}

        val phoneNumbers = HashSet<String>()

        elementsWithText.forEach {
            val matches = regex.findAll(it)
            phoneNumbers.addAll(matches.map { it.value}.toSet())
        }

        return phoneNumbers
    }
}