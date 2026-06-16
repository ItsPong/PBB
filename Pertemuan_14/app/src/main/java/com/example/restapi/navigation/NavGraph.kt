package com.example.restapi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.restapi.ui.screens.DetailScreen
import com.example.restapi.ui.screens.HomeScreen
import com.example.restapi.viewmodel.NewsViewModel

@Composable
fun AppNavGraph() {

    val navController = rememberNavController()
    val viewModel = viewModel<NewsViewModel>()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {

        composable("home") {
            HomeScreen(viewModel = viewModel) { article ->
                viewModel.selectArticle(article)
                navController.navigate("detail")
            }
        }

        composable("detail") {
            val article by viewModel.selectedArticle.collectAsState()
            article?.let {
                DetailScreen(
                    article = it,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
