package com.example.temantanam.ui.screen.analyzeenvironment

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.temantanam.navigation.Screen
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import com.example.compose.TemanTanamTheme
import com.example.temantanam.MainActivity
import com.example.temantanam.R
import com.example.temantanam.data.remote.ApiConfig
import com.example.temantanam.data.remote.ApiService
import com.example.temantanam.model.AnalyzeEnvironmentModel
import com.example.temantanam.model.AnalyzeEnvironmentResponse
import com.example.temantanam.ui.component.LoadingDialog
import com.example.temantanam.ui.theme.Poppins
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Integer.parseInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyzeEnvironmentScreen(
    navController : NavHostController
){
    var rainfallInput by remember { mutableStateOf("") }
    var soilTemperatureInput by remember { mutableStateOf("") }
    var humidityInput by remember { mutableStateOf("") }
    var phLevelInput by remember { mutableStateOf("") }
    var natriumLevelInput by remember { mutableStateOf("") }
    var kaliumLevelInput by remember { mutableStateOf("") }
    var phosporusLevelInput by remember { mutableStateOf("") }

    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog == true) {
        LoadingDialog()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 24.dp)
            .verticalScroll(state = rememberScrollState(), reverseScrolling = true, enabled = true),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Analyze Environment",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Poppins
            )
            Text(
                text = "Analyze the growth environment based on \n" +
                    "the soil temperature and the environment \n" +
                    "altitudes",
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Light,
                fontFamily = Poppins
            )
        }
        
        Spacer(modifier = Modifier.height(50.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(minHeight = 54.dp),
                value = rainfallInput,
                onValueChange = { rainfallInput = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(
                        text = "Rainfall",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Light
                    )
                },
                leadingIcon = {
                    Icon(painter = painterResource(R.drawable.ic_rainfall), contentDescription = "")
                }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(minHeight = 54.dp),
                value = soilTemperatureInput,
                onValueChange = { soilTemperatureInput = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(
                        text = "Soil Temperature",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Light
                    )
                },
                leadingIcon = {
                    Icon(painter = painterResource(R.drawable.ic_soil_temperature), contentDescription = "")
                }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(minHeight = 54.dp),
                value = humidityInput,
                onValueChange = { humidityInput = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(
                        text = "Humidity",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Light
                    )
                },
                leadingIcon = {
                    Icon(painter = painterResource(R.drawable.ic_humidity), contentDescription = "")
                }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(minHeight = 54.dp),
                value = phLevelInput,
                onValueChange = { phLevelInput = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(
                        text = "pH Level",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Light
                    )
                },
                leadingIcon = {
                    Icon(painter = painterResource(R.drawable.ic_chemical), contentDescription = "")
                }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(minHeight = 54.dp),
                value = natriumLevelInput,
                onValueChange = { natriumLevelInput = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(
                        text = "Natrium (Na) Level (optional)",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Light
                    )
                },
                leadingIcon = {
                    Icon(painter = painterResource(R.drawable.ic_chemical), contentDescription = "")
                }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(minHeight = 54.dp),
                value = kaliumLevelInput,
                onValueChange = { kaliumLevelInput = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(
                        text = "Kalium (K) Level (optional)",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Light
                    )
                },
                leadingIcon = {
                    Icon(painter = painterResource(R.drawable.ic_chemical), contentDescription = "")
                }
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .sizeIn(minHeight = 54.dp),
                value = phosporusLevelInput,
                onValueChange = { phosporusLevelInput = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
                placeholder = {
                    Text(
                        text = "Phosporus (P) Level (optional)",
                        fontFamily = Poppins,
                        fontWeight = FontWeight.Light
                    )
                },
                leadingIcon = {
                    Icon(painter = painterResource(R.drawable.ic_chemical), contentDescription = "")
                }
            )
        }

        Spacer(modifier = Modifier.height(75.dp))

        FilledTonalButton(
            onClick = {
                showDialog = true

                if (kaliumLevelInput == "") kaliumLevelInput = "0"
                if (natriumLevelInput == "") natriumLevelInput = "0"
                if (phosporusLevelInput == "") phosporusLevelInput = "0"

                val data = AnalyzeEnvironmentModel(
                    N = parseInt(natriumLevelInput),
                    P = parseInt(phosporusLevelInput),
                    K = parseInt(kaliumLevelInput),
                    temperature = parseInt(soilTemperatureInput),
                    humidity = parseInt(humidityInput),
                    ph = parseInt(phLevelInput),
                    rainfall = parseInt(rainfallInput)
                )

                val apiService = ApiConfig().getApiService()
                val postData = apiService.postAnalyzedData(data)
                postData.enqueue(object : Callback<AnalyzeEnvironmentResponse> {
                    override fun onResponse(
                        call: Call<AnalyzeEnvironmentResponse>,
                        response: Response<AnalyzeEnvironmentResponse>,
                    ) {
                        if (response.isSuccessful) {
                            val resposeBody = response.body()
                            Log.d("RESPONSEBODY", resposeBody!!.plant)
                            MainActivity.RESPONSERESULT.value = resposeBody

                            showDialog = false
                            navController.navigate("details")
                        }
                    }

                    override fun onFailure(call: Call<AnalyzeEnvironmentResponse>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
                    }

                })
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .sizeIn(minHeight = 54.dp)
        ) {
            Text(text = "Find plant!", fontFamily = Poppins, fontWeight = FontWeight.Light)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnalyzeEnvironmentScreenPreview() {
    TemanTanamTheme {
        AnalyzeEnvironmentScreen(navController = NavHostController(LocalContext.current))
    }
}