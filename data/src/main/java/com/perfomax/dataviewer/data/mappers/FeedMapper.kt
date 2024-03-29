package com.perfomax.dataviewer.data.mappers

import com.perfomax.dataviewer.domain.models.Feed

internal fun String.toDomainFeed(): Feed {
    return Feed(
        projectName = this.split("projectName:")[1].split(";")[0],
        feedName = this.split("feedName:")[1].split(";")[0],
        feedElement = this.split("feedElement:")[1].split(";")[0],
        feedElementCount = this.split("feedElementCount:")[1].split(";")[0].toInt(),
        feedUrl = this.split("feedUrl:")[1].split(";")[0],
        feedUpdateTime =  this.split("feedUpdateTime:")[1].split(";")[0],
        feedLoadTime = this.split("feedLoadTime:")[1].split(";")[0]
    )
}