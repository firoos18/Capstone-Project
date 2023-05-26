package com.example.temantanam.navigation

sealed class Screen(val route : String) {
    object Home : Screen("home")
    object Login : Screen("login")
    object Register : Screen("register")
    object CurrentWeather : Screen("currentWeather")
    object AnalyzeEnvironment : Screen("analyzeEnvironment")
    object Plantcycopedia : Screen("plantcycopedia")
    object Camera : Screen("camera")
    object Collections : Screen("collections")
}