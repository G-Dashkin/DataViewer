package com.example.dataviewer.data.network.api

fun main() {
    val testMap = hashMapOf<String, Int>()
    testMap["yml_catalog"] = 1
    println(testMap["yml_catalog"])
    testMap["yml_catalog"] = testMap["yml_catalog"]!!.inc()
    println(testMap["yml_catalog"])

}