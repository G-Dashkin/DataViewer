package com.example.dataviewer.data.network.api.parser3

class NewParser3 {

    companion object {


        // Конвертация xml-фида в массив
        fun parsingToList(feed: String): List<String> {

            val feedElements = ArrayList<String>() // Массив элементов фида
            var oldElement: String
            var newElement: String
            val processedFeed = feed.replace("\\s+".toRegex(), " ").replace("> <", "><")

            processedFeed.split("><").forEach { offerTag ->
                var varTag = offerTag
                if (varTag.first().toString() == "<") varTag = varTag.replace("<", "")
                else if (varTag.last().toString() == ">") varTag = varTag.replace(">", "")

                if (feedElements.isEmpty()) {
                    feedElements.add("<$varTag>") // Заполняем массив элементов фида строками/тэгами
                } else {
                    oldElement = feedElements.last().split(" ")[0].replace("<","")
                    newElement = varTag.replace("/","")

                    if (oldElement == newElement) {
                        if (feedElements.size < 1500) feedElements.set(feedElements.size-1, feedElements.last() + "<$varTag>")
                    } else {
                        // Нужно доработать...
//                        if(checkSameElements(feedElements, varTag)) feedElements.add("<$varTag>") else

//                        println(feedElements.slice(feedElements.size - 9..feedElements.size - 1))
//                        println(feedElements.contains(feedElements.last().split(" ")[0]+">" + "..."))
                        if(checkSameElements(feedElements, varTag) && feedElements.size < 1500) {
                            feedElements.add("<$varTag>")
//                        } else if() {
                        } else {
//                            while ()
                            feedElements.set(feedElements.size-1, feedElements.last().split(" ")[0]+">" + "...")
                        }
//                        if (feedElements.size < 1500) feedElements.add("<$varTag>")
                    }
                }

            }
            return feedElements
        }

        // Классная функция)))
        fun checkSameElements(arrayFeed: ArrayList<String>, newElement: String): Boolean {

            val newNewArray = ArrayList<String>()
            val newNewElement = newElement.split(" ")[0]

            if (arrayFeed.size >= 9) {

                val newArray = arrayFeed.slice(arrayFeed.size - 9..arrayFeed.size - 1) as ArrayList

                //----------------------------------------------------------------------------------
                println("----------Массив без измененеий------------")
                newArray.forEach { println(it) }
                println("-------------------------------------------")
                println("----------Передаваемый элемент-------------")
                println(newNewElement)
                println("$newNewElement>...")
                println("-------------------------------------------")


//                newArray.forEach { println(it.split(" ")[0].replace("<","")) }
                newArray.add(newNewElement)

                for (i in newArray) {
                    newNewArray.add(i.split(" ")[0].replace("<",""))
                }
            }

//            newNewArray.forEach { it }
//            println(arrayFeed.last().split(" ")[0].replace("<","")+">" + "...")
//            newNewArray.remove(newNewArray.last().split(" ")[0]+">" + "...".replace("<",""))
//            newNewArray.forEach { println(it.split(" ")[0].replace("<","")) }
            println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>")
            println("size != 1: " + (newNewArray.toSet().size != 1).toString())
            println("!contains: " + !newNewArray.contains("$newNewElement>..."))
            println("    Общее: ${(newNewArray.toSet().size != 1) && (!newNewArray.contains("$newNewElement>..."))}")
            return (newNewArray.toSet().size != 1) && (!newNewArray.contains("$newNewElement>..."))
        }

    }
}
