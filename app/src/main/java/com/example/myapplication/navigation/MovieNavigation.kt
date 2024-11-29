package com.example.myapplication.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.screens.home.HomeScreen
import com.example.myapplication.screens.home.details.DetailsScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = MoviesScreen.HomeScreen.name) {
        composable(MoviesScreen.HomeScreen.name) {
            HomeScreen(navController)
        }

        composable(
            MoviesScreen.DetailsScreen.name+"/{movie}",
            arguments = listOf(navArgument(name = "movie") {type = NavType.StringType})
            ) { arg ->
            DetailsScreen(
                navController,
                id = arg.arguments?.getString("movie"))
        }
    }
}