package com.example.temantanam.model

import com.example.temantanam.navigation.Screen

data class MenuItem(
    val id : Int,
    val title : String,
    val subTitle : String,
    val screen: Screen,
    val icon : Int
)