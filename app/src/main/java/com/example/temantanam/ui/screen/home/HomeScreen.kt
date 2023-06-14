package com.example.temantanam.ui.screen.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.TemanTanamTheme
import com.example.temantanam.MainActivity
import com.example.temantanam.R
import com.example.temantanam.data.remote.ApiConfig
import com.example.temantanam.model.AnalyzeEnvironmentModel
import com.example.temantanam.model.AnalyzeEnvironmentResponse
import com.example.temantanam.model.MenuItemData
import com.example.temantanam.navigation.Screen
import com.example.temantanam.ui.theme.Poppins
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    auth : FirebaseAuth
) {
//    var rainfallInput by remember { mutableStateOf("") }
//    var soilTemperatureInput by remember { mutableStateOf("") }
//    var humidityInput by remember { mutableStateOf("") }
//    var phLevelInput by remember { mutableStateOf("") }
//    var natriumLevelInput by remember { mutableStateOf("") }
//    var kaliumLevelInput by remember { mutableStateOf("") }
//    var phosporusLevelInput by remember { mutableStateOf("") }
//
//    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(start = 24.dp, end = 24.dp, top = 80.dp)
    ) {
        Text(
            text = "Teman Tanam, your crop \n" +
                    "planting assistants!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = Poppins
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Teman Tanam help you identify what kind of crops \n" +
                    "that suits your environment and identify \n" +
                    "whether your crops is healthy",
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = Poppins
        )
        Spacer(modifier = Modifier.size(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(MenuItemData.menuItems, key = {it.id}) {item ->
                MenuItem(
                    title = item.title,
                    subTitle = item.subTitle,
                    screen = item.screen,
                    navController = navController,
                    icon = item.icon
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(
    navController: NavHostController = rememberNavController(),
    auth : FirebaseAuth = Firebase.auth
) {
    TemanTanamTheme {
        HomeScreen(navController = navController, auth)
    }
}

@Composable
fun MenuItem(
    title : String,
    subTitle : String,
    screen : Screen,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    icon : Int
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .sizeIn(minHeight = 100.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .clickable {
                navController.navigate(screen.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                }
            }
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painterResource(icon),
                contentDescription = "Icon",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onPrimary
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Column(
                modifier = Modifier.width(250.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Poppins,
                    color = MaterialTheme.colorScheme.onPrimary,
//                    style = MaterialTheme.typography.titleSmall,
                )
                Text(
                    text = subTitle,
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontFamily = Poppins
//                    style = MaterialTheme.typography.bodySmall
                )
            }
            IconButton(
                modifier = Modifier
                    .size(24.dp),
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                },
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_chevron_right),
                    contentDescription = "Go to $title",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun MenuItemPreview(
    navController: NavHostController = rememberNavController()
) {
    TemanTanamTheme {
        MenuItem(
            title = "Current Weather",
            subTitle = "Giving plants recommendation based on\n" +
                    "weather forecast",
            screen = Screen.CurrentWeather,
            navController = navController,
            icon = R.drawable.ic_rainfall
        )
    }
}