package com.example.myapplication.navigation

enum class MoviesScreen {
    HomeScreen,
    DetailsScreen;

    companion object  {
        fun fromRoute(route: String?): MoviesScreen
        = when (route?.substringBefore("/")) {
            HomeScreen.name -> HomeScreen
            DetailsScreen.name -> DetailsScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("cannot find the route")
        }
    }
}