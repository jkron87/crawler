package crawler

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection

class SocketConnection {
    @Throws(IOException::class)
    fun getURLSource(url: String?): String {
        val urlObject = URL(url)
        val urlConnection: URLConnection = urlObject.openConnection()
//        urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11")
        return toString(urlConnection.getInputStream())
    }

    @Throws(IOException::class)
    private fun toString(inputStream: InputStream): String {
        BufferedReader(InputStreamReader(inputStream, "UTF-8")).use { bufferedReader ->
            var inputLine: String?
            val stringBuilder = StringBuilder()
            while (bufferedReader.readLine().also { inputLine = it } != null) {
                stringBuilder.append(inputLine)
            }
            return stringBuilder.toString()
        }
    }
}