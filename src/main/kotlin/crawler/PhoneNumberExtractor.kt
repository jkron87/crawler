package crawler

import java.util.regex.Pattern

class PhoneNumberExtractor {
    companion object{
    }

    fun findPhoneNumbers(sourceCode: String):Set<String> {
        val regex = Regex("\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})")
        val matches = regex.findAll(sourceCode)
        val urls: Sequence<String> = matches.map { it.value}
        return urls.toSet()
    }
}