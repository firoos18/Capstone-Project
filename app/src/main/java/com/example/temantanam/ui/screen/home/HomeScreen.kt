package com.example.temantanam.ui.screen.home

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.temantanam.R
import com.example.temantanam.model.MenuItemData
import com.example.temantanam.navigation.Screen
import com.example.temantanam.ui.theme.TemanTanamTheme

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 120.dp)
    ) {
        Text(
            text = "Let’s find how to optimize \n" +
                    "your plant growth!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
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
                    navController = navController
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview(
    navController: NavHostController = rememberNavController()
) {
    TemanTanamTheme {
        HomeScreen(navController = navController)
    }
}

@Composable
fun MenuItem(
    title : String,
    subTitle : String,
    screen : Screen,
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .sizeIn(minHeight = 100.dp)
            .fillMaxWidth()
            .background(Color(0xffD9D9D9))
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.width(280.dp)
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = subTitle,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                },
            ) {
                Icon(
                    painter = painterResource(R.drawable.chevron_right),
                    contentDescription = "Go to $title"
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
            navController = navController
        )
    }
}