package com.example.temantanam.ui.screen.plantcycopedia

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.temantanam.R
import androidx.compose.runtime.*
import com.example.temantanam.ui.component.PlantItem
import com.example.temantanam.ui.theme.TemanTanamTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlantcycopediaScreen() {
    var findPlantInput by remember{ mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Plantcycopedia",
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
            )
            Text(
                text = "Find some information about plant \n" +
                    "and how to treat them",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Light,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.size(80.dp))

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(minHeight = 54.dp),
            value = findPlantInput,
            onValueChange = {},
            singleLine = true,
            shape = RoundedCornerShape(10.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            placeholder = {
                Text(text = "Find plants!")
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = "Location")
            }
        )

        Spacer(modifier = Modifier.size(32.dp))

        LazyColumn(){
            items(count = 20) {
                PlantItem(latinName = "Oryza Sativa", englishName = "Rice")
                Spacer(modifier = Modifier.sizeIn(minHeight = 16.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlantcycopediaScreenPreview() {
    TemanTanamTheme {
        PlantcycopediaScreen()
    }
}