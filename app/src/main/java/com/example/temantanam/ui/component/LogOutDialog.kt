@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.temantanam.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.compose.TemanTanamTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogOutDialog(
    auth: FirebaseAuth,
    navController : NavHostController,
    closeDialog : () -> Unit
) {
    AlertDialog(onDismissRequest = {}) {
        Column(
            modifier = Modifier
                .size(height = 150.dp, width = 200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Are you sure?")
            Row() {
                TextButton(onClick = {
                    auth.signOut()
                    navController.navigate("login") {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                    }
                }) {
                    Text(
                        text = "Log Out",
                        color = MaterialTheme.colorScheme.error
                    )
                }
                TextButton(onClick = closeDialog) {
                    Text(
                        text = "Cancel",
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun LogOutDialogPreview() {
    TemanTanamTheme {
        LogOutDialog(auth = Firebase.auth, navController = rememberNavController(), closeDialog = {})
    }
}