package com.example.dataviewer.data.network.api

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

        val url = URL(PIZZA)
        val con = url.openConnection() as HttpURLConnection
        val citiFeed = con.inputStream.bufferedReader().use { it.readText() }

//    val arrayFeed = Parser.parsingToList(mockFeed)

        val arrayFeed = Parser.parsingToList(citiFeed)

        return arrayFeed

//    val url = URL(PIZZA)
//    val con = url.openConnection() as HttpURLConnection
//    val citiFeed = con.inputStream.bufferedReader().use { it.readText() }


//        val url = URL(apiUrl)
//        val con = url.openConnection() as HttpURLConnection
//        val data = con.inputStream.bufferedReader().use { it.readText() }

//        Log.d("MyLog", data)

//        val jsonAdapter = moshi.adapter(ShipmentsResponse::class.java)
//        val response = jsonAdapter.fromJson(json) as ShipmentsResponse
    }
}
