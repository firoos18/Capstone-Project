package com.example.temantanam.ui.screen.camera

import android.content.Context
import android.graphics.Bitmap
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.vision.detector.Detection
import org.tensorflow.lite.task.vision.detector.ObjectDetector

object ObjectDetection {

    fun detect(context : Context, image : Bitmap) : List<Detection>? {
        val optionsBuilder =
            ObjectDetector.ObjectDetectorOptions.builder()
                .setScoreThreshold(0.4f)
                .setMaxResults(1)

        val objectDetector =
            ObjectDetector.createFromFileAndOptions(
                context, "Plant_Disease_MobileNet_Metadata.tflite", optionsBuilder.build())

        return objectDetector?.detect(TensorImage.fromBitmap(image))
    }

}