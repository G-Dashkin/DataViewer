package com.perfomax.dataviewer.domain.repository

interface FeedRepository {
    suspend fun get(): List<String>
}