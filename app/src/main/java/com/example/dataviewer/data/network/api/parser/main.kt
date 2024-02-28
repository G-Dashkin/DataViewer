package com.example.dataviewer.data.network.api.parser

import com.example.dataviewer.data.network.api.parser2.NewParser2
import com.example.dataviewer.data.network.api.parser2.TestFeed2

fun main() {
    val mockFeed = TestFeed2().mockFeed
    println(NewParser2.parsing(mockFeed))
}