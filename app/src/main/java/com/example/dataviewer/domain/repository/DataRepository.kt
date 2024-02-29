package com.example.dataviewer.domain.repository

interface DataRepository {
    fun get():List<String>
}