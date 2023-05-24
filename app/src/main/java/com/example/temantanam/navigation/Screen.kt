package com.example.temantanam.navigation

sealed class Screen(val route : String) {
    object Home : Screen("home")
    object Login : Screen("login")
    object Register : Screen("register")
}