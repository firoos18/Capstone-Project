package com.example.temantanam.model

import com.example.temantanam.R
import com.example.temantanam.navigation.Screen

object MenuItemData {
    val menuItems = listOf(
        MenuItem(
            id = 1,
            title = "Analyze Environment",
            subTitle = "Analyze the growth environment based on \n" +
                    "the soil temperature and the environment \n" +
                    "altitudes",
            screen = Screen.AnalyzeEnvironment,
            icon = R.drawable.ic_chemical
        ),
        MenuItem(
            id = 2,
            title = "Detect Plant Disease",
            subTitle = "Find out whether your plant is healty \n" +
                    "or not",
            screen = Screen.Camera,
            icon = R.drawable.ic_camera
        ),
    )
}