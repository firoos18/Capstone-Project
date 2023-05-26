package com.example.temantanam.model

import com.example.temantanam.navigation.Screen

object MenuItemData {
    val menuItems = listOf(
        MenuItem(
            id = 1,
            title = "Current Weather",
            subTitle = "Giving plants recommendation based on\n" +
                    "weather forecast",
            screen = Screen.CurrentWeather
        ),
        MenuItem(
            id = 2,
            title = "Analyze Environment",
            subTitle = "Analyze the growth environment based on \n" +
                    "the soil temperature and the environment \n" +
                    "altitudes",
            screen = Screen.AnalyzeEnvironment
        ),
        MenuItem(
            id = 3,
            title = "Plantcycopedia",
            subTitle = "Find some information about plant \n" +
                    "and how to treat them",
            screen = Screen.Plantcycopedia
        ),
    )
}