package com.example.temantanam.ui.screen.camera

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrowseGallery
import androidx.compose.material.icons.sharp.BrowseGallery
import androidx.compose.material.icons.sharp.Lens
import androidx.compose.material.icons.sharp.PhotoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import androidx.compose.runtime.*
import androidx.core.net.toUri
import com.example.temantanam.MainActivity

@Composable
fun CameraScreen(
    outputDirectory: File,
    executor: Executor,
    onImageCaptured: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit
){
    val lensFacing = CameraSelector.LENS_FACING_BACK
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val preview = Preview.Builder().build()
    val previewView = remember { PreviewView(context) }
    val imageCapture : ImageCapture = remember { ImageCapture.Builder().build() }
    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(lensFacing)
        .build()

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
            MainActivity.PHOTO_URI = uri
            MainActivity.SHOULD_SHOW_CAMERA.value = false
            MainActivity.SHOULD_SHOW_PHOTO.value = true
        }
    )

    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )

        preview.setSurfaceProvider(previewView.surfaceProvider)
    }
    
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        AndroidView({ previewView }, modifier = Modifier.fillMaxSize())

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth().padding(start = 24.dp, end = 24.dp, bottom = 20.dp)
        ) {
            Spacer(modifier = Modifier.width(50.dp))

            IconButton(
                onClick = {
                    takePhoto(
                        fileNameFormat = "yyyy-MM-dd-HH-mm-ss-SSSS",
                        imageCapture = imageCapture,
                        outputDirectory = outputDirectory,
                        executor = executor,
                        onImageCaptured = onImageCaptured,
                        onError = onError
                    )
                },
                content = {
                    Icon(
                        imageVector = Icons.Sharp.Lens,
                        contentDescription = "Take picture",
                        tint = Color.White,
                        modifier = Modifier
                            .size(100.dp)
                            .padding(1.dp)
                            .border(1.dp, Color.White, CircleShape)
                    )
                }
            )

            IconButton(
                onClick = {
                    photoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                content = {
                    Icon(
                        imageVector = Icons.Sharp.PhotoLibrary,
                        contentDescription = "Pick Image from Gallery",
                        tint = Color.White,
                        modifier = Modifier
                            .size(100.dp)
                    )
                }
            )
        }
    }
}

private fun takePhoto(
    fileNameFormat : String,
    imageCapture : ImageCapture,
    outputDirectory : File,
    executor: Executor,
    onImageCaptured : (Uri) -> Unit,
    onError : (ImageCaptureException) -> Unit
) {
    val photoFile = File(
        outputDirectory,
        SimpleDateFormat(fileNameFormat, Locale.US).format(System.currentTimeMillis()) + ".jpg"
    )

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    imageCapture.takePicture(outputOptions, executor, object : ImageCapture.OnImageSavedCallback {
        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
            val savedUri = Uri.fromFile(photoFile)
            onImageCaptured(savedUri)
        }

        override fun onError(exception: ImageCaptureException) {
            Log.e("ImageCaptureError", "Take photo error", exception)
            onError(exception)
        }

    })
}

private suspend fun Context.getCameraProvider() : ProcessCameraProvider = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also { cameraProvider ->
        cameraProvider.addListener({
            continuation.resume(cameraProvider.get())
        }, ContextCompat.getMainExecutor(this))
    }
}