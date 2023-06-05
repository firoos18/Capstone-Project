package com.example.temantanam

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.example.temantanam.ui.screen.camera.CameraScreen
import com.example.temantanam.ui.theme.TemanTanamTheme
import java.io.File
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : ComponentActivity() {
    private lateinit var outputDirectory : File
    private lateinit var cameraExecutor: ExecutorService

    private var shouldShowCamera : MutableState<Boolean> = mutableStateOf(false)

    private var shouldShowPhoto : MutableState<Boolean> = mutableStateOf(false)

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            SHOULD_SHOW_CAMERA.value = true
        } else {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TemanTanamTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TemanTanamApp(
                        shouldShowCamera = SHOULD_SHOW_CAMERA,
                        cameraExecutor = cameraExecutor,
                        outputDirectory = outputDirectory,
                        shouldShowPhoto = SHOULD_SHOW_PHOTO,
                        handleImageCapture = ::handleImageCapture
                    )
                }
            }
        }
        requestCameraPermission()

        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                SHOULD_SHOW_CAMERA.value = true
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) -> {

            }

            else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun handleImageCapture(uri : Uri) {
        Log.e("PhotoUri", "Photo Uri : $uri")
        SHOULD_SHOW_CAMERA.value = false

        PHOTO_URI = uri
        SHOULD_SHOW_PHOTO.value = true
    }

    private fun getOutputDirectory() : File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }

        return if(mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    companion object {
        var PHOTO_URI : Uri? = "".toUri()

        var SHOULD_SHOW_PHOTO : MutableState<Boolean> = mutableStateOf(false)
        var SHOULD_SHOW_CAMERA : MutableState<Boolean> = mutableStateOf(false)
    }
}