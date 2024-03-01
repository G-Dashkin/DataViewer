package com.example.dataviewer.data.network.api

import android.os.Environment
import com.example.dataviewer.data.network.api.test.TestFeed
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class FeedApiNetwork(
    private val apiUrl: String
//    private val moshi: Moshi
): FeedApi {

    override fun getData(): List<String> {

        val mockFeed = TestFeed().mockFeed

        val CITI = "https://feeds-mic.s1.citilink.ru/context/context_msk_cl.xml"
        val PIZZA = "https://api2.kiparo.com/static/it_news.xml"

        val url = URL(apiUrl)
        val connection = url.openConnection() as HttpURLConnection

        val citiFeed = connection.inputStream.bufferedReader().use { it.readText() }

        val arrayFeed = Parser.parsingToList(citiFeed)

        return arrayFeed

    }
}
