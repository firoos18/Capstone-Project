package com.example.temantanam.model

import com.example.temantanam.R

object WeatherInfoData {
    val weatherInfoItems = listOf(
        WeatherInfo(
            icon = R.drawable.ic_rainfall,
            title = "Rainfall",
            contentDescription = "Rainfall"
        ),
        WeatherInfo(
            icon = R.drawable.ic_soil_temperature,
            title = "Soil Temperature",
            contentDescription = "Soil Temperature",
        ),
        WeatherInfo(
            icon = R.drawable.ic_humidity,
            title = "Humidity",
            contentDescription = "Humidity"
        ),
    )
}