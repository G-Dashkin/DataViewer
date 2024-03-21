package com.perfomax.dataviewer.data.network.api

class Parser {

    companion object {

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
                        if (feedElements.size < 500) feedElements.set(feedElements.size-1, feedElements.last() + "<$varTag>")
                    } else {
                        if(checkSameElement(feedElements, varTag) && feedElements.size < 500) {
                            feedElements.add("<$varTag>")
                        } else {
                            feedElements.set(feedElements.size-1, feedElements.last().replace(">...", ""))
                            feedElements.set(feedElements.size-1, feedElements.last().split(" ")[0]+">" + "...")
                        }
                    }
                }
            }
            return feedElements
        }

        fun checkSameElement(arrayFeed: ArrayList<String>, addedElement: String): Boolean {

            var newNewArray = ArrayList<String>()
            val newAddedElement = addedElement.split(" ")[0]

            if (arrayFeed.size >= 9) {
                newNewArray = arrayFeed.slice(arrayFeed.size - 9..arrayFeed.size - 1) as ArrayList
                newNewArray.add(newAddedElement)
                newNewArray.replaceAll { it.split(" ")[0].replace("<","").replace(">"," ").split(" ")[0] }
            }
            return newNewArray.toSet().size != 1
        }

    }
}
