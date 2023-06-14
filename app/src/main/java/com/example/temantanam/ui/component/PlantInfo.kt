package com.example.temantanam.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.TemanTanamTheme
import com.example.temantanam.model.PlantInfoModel
import com.example.temantanam.R

@Composable
fun PlantInfo(
    plantInfoModel: PlantInfoModel
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 4.dp)
    ) {
        Icon(
            painter = painterResource(id = plantInfoModel.icon),
            contentDescription = "Icon",
            Modifier.size(24.dp),
            tint = MaterialTheme.colorScheme.onSurface
        )
        Column {
            Text(
                text = plantInfoModel.title,
                fontSize = 8.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = plantInfoModel.value,
                fontSize = 8.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlantInfoPreview() {
    TemanTanamTheme {
        PlantInfo(plantInfoModel = PlantInfoModel(R.drawable.ic_soil_temperature, "Climate", "18-30°C"))
    }
}