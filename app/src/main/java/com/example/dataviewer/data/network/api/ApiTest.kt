package com.example.dataviewer.data.network.api

import android.util.Log
import java.net.HttpURLConnection
import java.net.URL
import java.text.DecimalFormat

fun main() {
    val CITI = "https://feeds-mic.s1.citilink.ru/context/context_msk_cl.xml"

    val url = URL(CITI)
    val con = url.openConnection() as HttpURLConnection
    val data = con.inputStream.bufferedReader().use { it.readText() }

//    (data.toByteArray().size/1024).toSeparate()
//    data.toByteArray()
//        .toString()
//        .apply {
//            println(this)
//        }

    println(data)



}

fun Any.toSeparate():String = DecimalFormat("#,###,###").format(this.toString().toInt()).apply { println("$this MB") }