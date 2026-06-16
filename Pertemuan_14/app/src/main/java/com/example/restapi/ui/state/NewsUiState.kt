package com.example.restapi.ui.state

import com.example.restapi.data.model.Article

sealed class NewsUiState {

    object Loading : NewsUiState()

    data class Success(
        val articles: List<Article>
    ) : NewsUiState()

    data class Error(
        val message: String
    ) : NewsUiState()
}
