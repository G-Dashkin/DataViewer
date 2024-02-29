package com.example.dataviewer.domain.repository

interface FeedRepository {
    suspend fun get(): List<String>
}