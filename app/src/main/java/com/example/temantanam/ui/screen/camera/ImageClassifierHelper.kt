package com.example.temantanam.ui.screen.camera

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.view.Surface
import androidx.compose.runtime.MutableState
import com.example.temantanam.MainActivity
import com.example.temantanam.ml.PlantDiseaseMobilenetMetadata
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions
import org.tensorflow.lite.task.vision.classifier.Classifications
import org.tensorflow.lite.task.vision.classifier.ImageClassifier

class ImageClassifierHelper(
    var threshold: Float = 0.8f,
    var numThreads: Int = 2,
    var maxResults: Int = 3,
    val context: Context,
    val imageClassifierListener: ClassifierListener?
) {
    private var imageClassifier: ImageClassifier? = null

    init {
        setupImageClassifier()
    }

    fun clearImageClassifier() {
        imageClassifier = null
    }

    private fun setupImageClassifier() {
        val optionsBuilder = ImageClassifier.ImageClassifierOptions.builder()
            .setScoreThreshold(threshold)
            .setMaxResults(maxResults)

        val baseOptionsBuilder = BaseOptions.builder().setNumThreads(numThreads)
        optionsBuilder.setBaseOptions(baseOptionsBuilder.build())

        val modelName = "Plant_Disease_MobileNet_Metadata.tflite"

        imageClassifier = ImageClassifier.createFromFileAndOptions(context, modelName, optionsBuilder.build())
    }

    fun classify(image : Bitmap) {
        if (imageClassifier == null) {
            setupImageClassifier()
        }

        val imageProcessor = ImageProcessor.Builder().build()

        val tensorImage = imageProcessor.process(TensorImage.fromBitmap(image))

        val results = imageClassifier?.classify(tensorImage)
        imageClassifierListener?.onResults(
            results,
        )

        if (results?.get(0)!!.categories.isNotEmpty()) {
            MainActivity.RESULT.value = results[0].categories[0].label
        }

    }

    private fun getOrientationFromRotation(rotation: Int) : ImageProcessingOptions.Orientation {
        when (rotation) {
            Surface.ROTATION_270 ->
                return ImageProcessingOptions.Orientation.BOTTOM_RIGHT
            Surface.ROTATION_180 ->
                return ImageProcessingOptions.Orientation.RIGHT_BOTTOM
            Surface.ROTATION_90 ->
                return ImageProcessingOptions.Orientation.TOP_LEFT
            else ->
                return ImageProcessingOptions.Orientation.RIGHT_TOP
        }
    }

    interface ClassifierListener {
        fun onError(error: String)
        fun onResults(
            results: List<Classifications>?,
        )
    }
}