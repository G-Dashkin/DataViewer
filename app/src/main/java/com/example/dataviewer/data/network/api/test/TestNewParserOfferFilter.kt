package com.example.dataviewer.data.network.api.test

class TestNewParserOfferFilter {

    companion object {

//        val notRepeatElementsList = arrayListOf<String>()

        fun parsing(feed: String): List<String> {

            val repeatElementsList = arrayListOf<String>()

            // Последовательность действий:
            // 1.Разделение строки по элементу "><"
            // 2.

            feed.split("><").forEach { offerTag ->
                var varTag = offerTag
                if (varTag.first().toString() == "<") varTag = varTag.replace("<", "")
                if (varTag.last().toString() == ">") varTag = varTag.replace(">", "")
                varTag = varTag.split(">")[0]
//                println(varTag)
    //        if (varTag.first().toString() == "/") varTag = varTag.replace("/", "")
    //        varTag = varTag.split(" ")[0]

    //        if (!varTag.contains(">") && !varTag.contains("<"))
//                println(repeatElementsList)
//                println(varTag)
                if (varTag.first().toString() != "/") repeatElementsList.add(varTag.split(" ")[0])
//                println(repeatElementsList)
//                println("---------------------------------------------------")
            }

            // По итогу мы получаем массив всех тэгов
            println(repeatElementsList)
            // Теперь нужно подсчитать колличество всех повторяющихся элементов

            repeatElementsList.groupingBy { it }.eachCount().forEach{
                println(it.toString().replace("=", " "))
            }

            val tableTags = repeatElementsList.groupingBy { it }.eachCount()
            println(tableTags.values.max())


            val newArray = repeatElementsList.filter { it == "offer" }
    //        println(repeatElementsList)
            return newArray
        }
    }
}