package com.example.restapi.data.repository

import com.example.restapi.data.api.RetrofitClient

class NewsRepository {

    suspend fun getNews() =
        RetrofitClient.apiService.getTopHeadlines(
            apiKey = "e93c09d1d53f4d44ae8870e4dd6d23e5"
        )
}
