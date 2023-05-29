package com.example.temantanam

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.temantanam.navigation.NavigationItem
import com.example.temantanam.navigation.Screen
import com.example.temantanam.ui.screen.analyzeenvironment.AnalyzeEnvironmentScreen
import com.example.temantanam.ui.screen.authentication.login.LoginScreen
import com.example.temantanam.ui.screen.authentication.register.RegisterScreen
import com.example.temantanam.ui.screen.camera.CameraScreen
import com.example.temantanam.ui.screen.collections.CollectionsScreen
import com.example.temantanam.ui.screen.currentweather.CurrentWeatherScreen
import com.example.temantanam.ui.screen.home.HomeScreen
import com.example.temantanam.ui.screen.plantcycopedia.PlantcycopediaScreen
import com.example.temantanam.ui.theme.TemanTanamTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemanTanamApp(
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentState = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
                 if (currentState != Screen.Plantcycopedia.route &&
                    currentState != Screen.CurrentWeather.route &&
                     currentState != Screen.Login.route &&
                     currentState != Screen.Register.route &&
                     currentState != Screen.AnalyzeEnvironment.route)
                 {
                     TopBarApp()
                 }
        },
        bottomBar = {
            if (currentState != Screen.Plantcycopedia.route &&
                currentState != Screen.CurrentWeather.route &&
                currentState != Screen.Login.route &&
                currentState != Screen.Register.route &&
                currentState != Screen.AnalyzeEnvironment.route)
            {
                BottomAppBar(navController = navController)
            }
        }
    ) {
       NavHost(navController = navController, startDestination = "home") {
           composable(Screen.Home.route) {
               HomeScreen(navController)
           }
           composable(Screen.Login.route) {
               LoginScreen()
           }
           composable(Screen.Register.route) {
               RegisterScreen()
           }
           composable(Screen.Camera.route) {
               CameraScreen()
           }
           composable(Screen.Collections.route) {
               CollectionsScreen()
           }
           composable(Screen.CurrentWeather.route) {
               CurrentWeatherScreen()
           }
           composable(Screen.AnalyzeEnvironment.route) {
               AnalyzeEnvironmentScreen()
           }
           composable(Screen.Plantcycopedia.route) {
               PlantcycopediaScreen()
           }
       }
    }
}

@Preview(showBackground = true)
@Composable
fun TemanTanamAppPreview() {
    TemanTanamTheme {
        TemanTanamApp()
    }
}

@Composable
fun TopBarApp(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp, horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(R.drawable.haerin),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .clip(CircleShape)
                .size(46.dp)
        )
        Column(Modifier.padding(start = 8.dp)) {
            Text(
                text = "Hello,",
                fontWeight = FontWeight.Light,
                fontSize = 14.sp
            )
            Text(
                text = "Haerin",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarAppPreview() {
    TemanTanamTheme {
        TopBarApp()
    }
}

@Composable
fun BottomAppBar(
    navController : NavHostController,
) {
    NavigationBar(
        modifier = Modifier.clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItem = listOf(
            NavigationItem(
                title = "Home",
                icon = R.drawable.ic_home,
                screen = Screen.Home,
                contentDescription = "Home Screen"
            ),
            NavigationItem(
                title = "Camera",
                icon = R.drawable.ic_camera,
                screen = Screen.Camera,
                contentDescription = "Camera Screen"
            ),
            NavigationItem(
                title = "Collections",
                icon = R.drawable.ic_collections,
                screen = Screen.Collections,
                contentDescription = "Collections Screen"
            )
        )

        NavigationBar {
            navigationItem.map { item ->
                NavigationBarItem(
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = item.contentDescription,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                    label = {
                        Text(
                            item.title,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                )
            }
        }
    }
}