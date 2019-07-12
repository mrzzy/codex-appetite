package com.treble.appetite.camera

import androidx.camera.core.CameraX
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureConfig
import androidx.lifecycle.LifecycleOwner

class DefaultCameraService(val lifecycleOwner: LifecycleOwner) : CameraService {
    override fun takePicture(imagePath: String) {
        val imageCaptureConfig = ImageCaptureConfig.Builder().build()
        val imageCapture = ImageCapture(imageCaptureConfig)

        CameraX.bindToLifecycle(lifecycleOwner, imageCapture)
    }
}