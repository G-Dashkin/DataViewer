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

    println(data)
//    (data.toByteArray().size/1024).toSeparate()
//    data.toByteArray()
//        .toString()
//        .apply {
//            println(this)
//        }
    //----------------------------------------------------------------------------------------------
//    val countOfSymbol = data.filter { it == '<' }.length
//    println(countOfSymbol)
    //----------------------------------------------------------------------------------------------




//    data

//    val occurrences = data.filter{ it in "</"}
//        .groupingBy { it }
//        .eachCount()

//    println(occurrences)
//
//    val s = "<"
//    val substringToFind = "</"
//
//    val start = s.indexOf(substringToFind)
//    val end = start + substringToFind.length
//
//    println(s.substring(start,end))


//    fun CharSequence.findFirstOf(chars: CharSequence) = indexOfFirst { it in chars }
//    fun CharSequence.findLastOf(chars: CharSequence) = indexOfLast { it in chars }
//    fun CharSequence.findFirstNotOf(chars: CharSequence) = indexOfFirst { it !in chars }
//    fun CharSequence.findLastNotOf(chars: CharSequence) = indexOfLast { it !in chars }
//
//    val s = "abcdefghi"
//    val chars = "aeiou"
//    println(s.findFirstOf(chars))
//    println(s.findFirstNotOf(chars))
//    println(s.findLastOf(chars))
//    println(s.findLastNotOf(chars))


    // поиск тэкга элемента
// признаки </

// признаки <

// if <


}

fun Any.toSeparate():String = DecimalFormat("#,###,###").format(this.toString().toInt()).apply { println("$this MB") }