package com.example.temantanam.ui.screen.authentication.login

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.TemanTanamTheme
import com.example.temantanam.ui.component.LoadingDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController : NavHostController,
    auth : FirebaseAuth
) {
    var emailInput by remember{ mutableStateOf("") }
    var passwordInput by remember{ mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog == true) {
        LoadingDialog()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, start = 24.dp, end = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Login",
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
                onValueChange = { emailInput = it },
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
                value = passwordInput,
                onValueChange = { passwordInput = it },
                singleLine = true,
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(text = "Password")
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Localized description for accessibility services
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    // Toggle button to hide or display password
                    IconButton(onClick = {passwordVisible = !passwordVisible}) {
                        Icon(image, description)
                    }
                }
            )
        }
        Spacer(modifier = Modifier.size(240.dp))
        FilledTonalButton(
            onClick = {
                showDialog = true
                val login = auth.signInWithEmailAndPassword(emailInput, passwordInput)

                login.addOnSuccessListener {
                    showDialog = false
                    navController.navigate("home")
                }
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(minHeight = 54.dp)
        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.size(64.dp))
        Row {
            Text(
                text = "Don’t have an account?",
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = " Register",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navController.navigate("register") {
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
fun LoginScreenPreview(
    navController: NavHostController = rememberNavController(),
    firebaseAuth: FirebaseAuth = Firebase.auth
) {
    TemanTanamTheme {
        LoginScreen(
            navController = navController,
            firebaseAuth
        )
    }
}