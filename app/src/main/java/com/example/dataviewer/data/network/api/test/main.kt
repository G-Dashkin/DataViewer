package com.example.dataviewer.data.network.api.test

import com.example.dataviewer.data.network.api.FeedApiNetwork

fun main() {
    val mockFeed = TestFeed().mockFeed

    val CITI = "https://feeds-mic.s1.citilink.ru/context/context_msk_cl.xml"
    val PIZZA = "https://api2.kiparo.com/static/it_news.xml"

//    val url = URL(PIZZA)
//    val con = url.openConnection() as HttpURLConnection
//    val citiFeed = con.inputStream.bufferedReader().use { it.readText() }

//    val arrayFeed = Parser.parsingToList(mockFeed)

    val arrayFeed = FeedApiNetwork(mockFeed).getData()

    arrayFeed.forEach {
        println(it)
    }

    println(arrayFeed.size)
}