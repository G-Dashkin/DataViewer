package com.example.dataviewer.data.network.api.parser3

import java.net.HttpURLConnection
import java.net.URL

fun main() {

    val mockFeed = TestFeed3().mockFeed

    val CITI = "https://feeds-mic.s1.citilink.ru/context/context_msk_cl.xml"
    val PIZZA = "https://api2.kiparo.com/static/it_news.xml"

//    val url = URL(PIZZA)
//    val con = url.openConnection() as HttpURLConnection
//    val citiFeed = con.inputStream.bufferedReader().use { it.readText() }

    val arrayFeed = NewParser3.parsingToList(mockFeed)

    arrayFeed.forEach {
        println(it)
    }

    println(arrayFeed.size)

}