package com.example.temantanam.ui.screen.camera

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.speech.RecognitionListener
import android.util.DisplayMetrics
import android.util.Log
import android.util.Size
import android.view.Display
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.camera.core.AspectRatio
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.ImageProxy
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrowseGallery
import androidx.compose.material.icons.sharp.BrowseGallery
import androidx.compose.material.icons.sharp.Lens
import androidx.compose.material.icons.sharp.PhotoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.net.toUri
import com.example.temantanam.MainActivity
import com.example.temantanam.ml.Generated
import com.example.temantanam.ml.PlantDiseaseMobilenet150
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import org.tensorflow.lite.task.vision.classifier.ImageClassifier
import java.nio.ByteBuffer
import java.util.concurrent.Executors

private lateinit var bitmapBuffer: Bitmap
private lateinit var imageClassifierHelper: ImageClassifierHelper

@RequiresApi(Build.VERSION_CODES.P)
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

    imageClassifierHelper =
        ImageClassifierHelper(context = LocalContext.current, imageClassifierListener = null)

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

//    val imageAnalyzer = ImageAnalysis.Builder()
//        .setTargetResolution(Size(1280, 720))
//        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
//        .build()
//        .also { analysisUseCase ->
//            analysisUseCase.setAnalyzer(Executors.newSingleThreadExecutor(), ImageAnalysis.Analyzer { imageProxy ->
//                val rotationDegrees = imageProxy.imageInfo.rotationDegrees
//
//                val model = PlantDiseaseMobilenet150.newInstance(context)
//                val model2 = Generated.newInstance(context)
//                val inputFeature = TensorBuffer.createFixedSize(intArrayOf(1, 150, 150, 3), DataType.FLOAT32)
////                inputFeature.loadBuffer(ByteBuffer.allocate(5))
//                val tfImage = TensorImage.fromBitmap(BitmapFactory.decodeFile(imageProxy.toString()))
//                val outputs = model2.process(tfImage.tensorBuffer)
//                val outputFeature = outputs.outputFeature0AsTensorBuffer
//                Log.d("OUTPUTFEATURE", outputFeature.toString())
//            })
//        }
    val cameraExecutor = Executors.newSingleThreadExecutor()

    val imageAnalyzer = ImageAnalysis.Builder()
        .setTargetAspectRatio(AspectRatio.RATIO_4_3)
        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        .setOutputImageFormat(ImageAnalysis.OUTPUT_IMAGE_FORMAT_RGBA_8888)
        .build()
        .also {
            it.setAnalyzer(cameraExecutor) { image ->
                if (!::bitmapBuffer.isInitialized) {
                    // The image rotation and RGB image buffer are initialized only once
                    // the analyzer has started running
                    bitmapBuffer = Bitmap.createBitmap(
                        image.width,
                        image.height,
                        Bitmap.Config.ARGB_8888
                    )
                }
                classifyImage(image)
            }
        }

    LaunchedEffect(lensFacing) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture,
            imageAnalyzer
        )

        preview.setSurfaceProvider(previewView.surfaceProvider)
    }
    
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        AndroidView({ previewView }, modifier = Modifier.fillMaxSize())

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = MainActivity.RESULT.value,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
            }
        }

//        Row(
//            horizontalArrangement = Arrangement.SpaceBetween,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(start = 24.dp, end = 24.dp, bottom = 20.dp)
//        ) {
//            Spacer(modifier = Modifier.width(50.dp))
//
//            IconButton(
//                onClick = {
//                    takePhoto(
//                        fileNameFormat = "yyyy-MM-dd-HH-mm-ss-SSSS",
//                        imageCapture = imageCapture,
//                        outputDirectory = outputDirectory,
//                        executor = executor,
//                        onImageCaptured = onImageCaptured,
//                        onError = onError
//                    )
//                },
//                content = {
//                    Icon(
//                        imageVector = Icons.Sharp.Lens,
//                        contentDescription = "Take picture",
//                        tint = Color.White,
//                        modifier = Modifier
//                            .size(100.dp)
//                            .padding(1.dp)
//                            .border(1.dp, Color.White, CircleShape)
//                    )
//                }
//            )
//
//            IconButton(
//                onClick = {
//                    photoPickerLauncher.launch(
//                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
//                    )
//                },
//                content = {
//                    Icon(
//                        imageVector = Icons.Sharp.PhotoLibrary,
//                        contentDescription = "Pick Image from Gallery",
//                        tint = Color.White,
//                        modifier = Modifier
//                            .size(100.dp)
//                    )
//                }
//            )
//        }
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
            MainActivity.PHOTO_PATH = photoFile.absolutePath
            onImageCaptured(savedUri)
        }

        override fun onError(exception: ImageCaptureException) {
            Log.e("ImageCaptureError", "Take photo error", exception)
            onError(exception)
        }

    })
}

@RequiresApi(Build.VERSION_CODES.P)
private suspend fun Context.getCameraProvider() : ProcessCameraProvider = suspendCoroutine { continuation ->
    ProcessCameraProvider.getInstance(this).also { cameraProvider ->
        cameraProvider.addListener({
            continuation.resume(cameraProvider.get())
        }, ContextCompat.getMainExecutor(this))
    }
}

private fun classifyImage(image: ImageProxy) {
    // Copy out RGB bits to the shared bitmap buffer
    image.use { bitmapBuffer.copyPixelsFromBuffer(image.planes[0].buffer) }

    // Pass Bitmap and rotation to the image classifier helper for processing and classification
    imageClassifierHelper.classify(bitmapBuffer)
}