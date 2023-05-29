package com.example.temantanam.model

import androidx.compose.ui.graphics.painter.Painter

data class WeatherInfo(
    val icon : Int,
    val title : String,
    val contentDescription : String,
    val amount : Double? = null
)
