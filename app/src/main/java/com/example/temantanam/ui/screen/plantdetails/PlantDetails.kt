package com.example.temantanam.ui.screen.plantdetails

import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.temantanam.MainActivity
import com.example.temantanam.R
import com.example.temantanam.model.AnalyzeEnvironmentModel
import com.example.temantanam.model.AnalyzeEnvironmentResponse
import com.example.temantanam.model.PlantInfoModel
import com.example.temantanam.ui.component.PlantInfo
import com.example.temantanam.ui.theme.TemanTanamTheme

@Composable
fun PlantDetails() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        AsyncImage(
            model = MainActivity.RESPONSERESULT.value.photoLink,
            contentDescription = "Photo",
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(maxHeight = 350.dp)
        )
        Column(
            modifier = Modifier
                .padding(start = 24.dp, end = 24.dp, top = 12.dp)
        ) {
            Column {
                Text(
                    text = MainActivity.RESPONSERESULT.value.plant,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = MainActivity.RESPONSERESULT.value.scientificName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light,
                    fontStyle = FontStyle.Italic
                )
            }

            Spacer(modifier = Modifier.height(36.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Description",
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
                Text(
                    text = MainActivity.RESPONSERESULT.value.description,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Justify
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                PlantInfo(plantInfoModel = PlantInfoModel(R.drawable.ic_soil_temperature, "Climate", MainActivity.RESPONSERESULT.value.climate))
                PlantInfo(plantInfoModel = PlantInfoModel(R.drawable.ic_harvest_time, "Harvest", MainActivity.RESPONSERESULT.value.harvest))
                PlantInfo(plantInfoModel = PlantInfoModel(R.drawable.ic_altitudes, "Altitudes", MainActivity.RESPONSERESULT.value.altitudes))
                PlantInfo(plantInfoModel = PlantInfoModel(R.drawable.ic_rainfall, "Rainfall", MainActivity.RESPONSERESULT.value.rainfall))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlantDetailsPreview() {
    TemanTanamTheme {
        PlantDetails()
    }
}