package com.example.temantanam.ui.screen.authentication.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.temantanam.ui.theme.TemanTanamTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController : NavHostController
) {
    var emailInput by remember{ mutableStateOf("") }
    var passwordInput by remember{ mutableStateOf("") }
    var rePasswordInput by remember{ mutableStateOf("") }
    var usernameInput by remember{ mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Register",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.size(230.dp))
        Column(
            horizontalAlignment = Alignment.End
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(minHeight = 54.dp),
                value = emailInput,
                onValueChange = {},
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(text = "Email")
                }
            )
            Spacer(modifier = Modifier.size(24.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(minHeight = 54.dp),
                value = usernameInput,
                onValueChange = {},
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(text = "Username")
                }
            )
            Spacer(modifier = Modifier.size(24.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(minHeight = 54.dp),
                value = passwordInput,
                onValueChange = {},
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(text = "Password")
                }
            )
            Spacer(modifier = Modifier.size(24.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(minHeight = 54.dp),
                value = rePasswordInput,
                onValueChange = {},
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(text = "Password")
                }
            )
        }
        Spacer(modifier = Modifier.size(65.dp))
        FilledTonalButton(
            onClick = {},
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(minHeight = 54.dp)
        ) {
            Text(text = "Register")
        }
        Spacer(modifier = Modifier.size(64.dp))
        Row {
            Text(
                text = "Already have an account?",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = " Login",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navController.navigate("login") {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview(
    navController: NavHostController = rememberNavController()
) {
    TemanTanamTheme {
        RegisterScreen(navController)
    }
}