package com.example.temantanam.ui.screen.authentication.register

import android.util.Log
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.TemanTanamTheme
import com.example.temantanam.ui.component.LoadingDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(
    navController : NavHostController,
    auth : FirebaseAuth
) {
    var emailInput by remember{ mutableStateOf("") }
    var passwordInput by remember{ mutableStateOf("") }
    var rePasswordInput by remember{ mutableStateOf("") }
    var usernameInput by remember{ mutableStateOf("") }

    var isError by rememberSaveable { mutableStateOf(false) }

    fun passwordCheck(password : String, rePassword : String) {
        isError = password != rePassword
    }

    var passwordVisible by remember { mutableStateOf(false) }
    var rePasswordVisible by remember { mutableStateOf(false) }

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog == true) {
        LoadingDialog()
    }

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
                value = usernameInput,
                onValueChange = { usernameInput = it },
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
                onValueChange = { passwordInput = it },
                singleLine = true,
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(text = "Password")
                },
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
            Spacer(modifier = Modifier.size(24.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(minHeight = 54.dp),
                value = rePasswordInput,
                onValueChange = {
                    rePasswordInput = it
                    passwordCheck(passwordInput, rePasswordInput)
                                },
                singleLine = true,
                visualTransformation = if (rePasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(text = "Re - Password")
                },
                isError = isError,
                supportingText = {
                    if (isError) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Password is not the same",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                trailingIcon = {
                    val image = if (rePasswordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    // Localized description for accessibility services
                    val description = if (rePasswordVisible) "Hide password" else "Show password"

                    // Toggle button to hide or display password
                    IconButton(onClick = {rePasswordVisible = !rePasswordVisible}) {
                        Icon(image, description)
                    }
                }
            )
        }
        Spacer(modifier = Modifier.size(65.dp))
        FilledTonalButton(
            onClick = {
                showDialog = true

                if (passwordInput == rePasswordInput) {
                    auth.createUserWithEmailAndPassword(emailInput, passwordInput)
                        .addOnSuccessListener {
                            showDialog = false
                            val updateProfile = UserProfileChangeRequest.Builder()
                                .setDisplayName(usernameInput)
                                .build()
                            auth.currentUser?.updateProfile(updateProfile)
                            navController.navigate("home")
                        }
                }
            },
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
    navController: NavHostController = rememberNavController(),
    auth: FirebaseAuth = Firebase.auth
) {
    TemanTanamTheme {
        RegisterScreen(navController, auth)
    }
}