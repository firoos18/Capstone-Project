package com.example.temantanam.ui.screen.currentweather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.compose.TemanTanamTheme
import com.example.temantanam.R
import com.example.temantanam.model.WeatherInfoData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrentWeatherScreen() {
    var locationInput by remember{ mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Current Weather",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "Giving plants recommendation based on\n" +
                    "weather forecast",
                textAlign = TextAlign.Center,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
        }

        Spacer(modifier = Modifier.size(64.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(minHeight = 54.dp),
            value = locationInput,
            onValueChange = {},
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                Text(text = "Find your location")
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_location),
                    contentDescription = "Location")
            }
        )

        Spacer(modifier = Modifier.size(36.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(WeatherInfoData.weatherInfoItems, key = {it.title}) { data ->
                WeatherInfo(
                    icon = data.icon,
                    title = data.title,
                    contentDescription = data.contentDescription
                )
            }
        }

        Spacer(modifier = Modifier.size(80.dp))

        FilledTonalButton(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(minHeight = 45.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = Color(0xffD9D9D9)
            )
        ) {
            Text(
                text = "Find plant!",
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentWeatherScreenPreview() {
    TemanTanamTheme {
        CurrentWeatherScreen()
    }
}

@Composable
fun WeatherInfo(
    modifier: Modifier = Modifier,
    icon : Int,
    title : String,
    contentDescription : String,
    amount : Double? = null
){
    Box(
        modifier
            .clip(RoundedCornerShape(8.dp))
            .size(height = 70.dp, width = 163.dp)
            .background(Color(0xffD9D9D9))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp)
        ) {
            Icon(
                painter = painterResource(icon),
                contentDescription = contentDescription,
                modifier = Modifier.size(36.dp)
            )
            Spacer(modifier = Modifier.size(4.dp))
            Column() {
                Text(
                    text = title,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = amount.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun WeatherInfoPreview(){
    TemanTanamTheme {
        WeatherInfo(
            icon = R.drawable.ic_rainfall,
            title = "Rainfall",
            contentDescription = "Rainfall",
            amount = 200.0
        )
    }
}