package com.example.temantanam.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.TemanTanamTheme
import com.example.temantanam.R

@Composable
fun PlantItem(
    modifier: Modifier = Modifier,
    latinName : String,
    englishName : String
) {
    Box(
        Modifier
            .fillMaxWidth()
            .sizeIn(minHeight = 100.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xffD9D9D9))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 10.dp, bottom = 10.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.img_blank),
                    contentDescription = "Plant Image",
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.size(8.dp))

                Column {
                    Text(
                        text = englishName,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = latinName,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Light,
                        fontStyle = FontStyle.Italic
                    )
                }
            }

            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(R.drawable.ic_chevron_right),
                    contentDescription = "More Info",
                    modifier = Modifier.size(14.dp)
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PlantItemPreview() {
    TemanTanamTheme {
        PlantItem(
            englishName = "Rice",
            latinName = "Oryza Sativa"
        )
    }
}