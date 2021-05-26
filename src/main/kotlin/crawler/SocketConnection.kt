package crawler

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.net.URLConnection

open class SocketConnection {
    open fun getURLSource(url: URL): String? {
        val urlConnection: URLConnection = url.openConnection()
        return try {
            toString(urlConnection.getInputStream())
        } catch (e: Exception) {
            null
        }
    }

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