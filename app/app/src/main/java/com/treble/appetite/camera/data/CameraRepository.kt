package com.treble.appetite.camera.data

class CameraRepository(
    val database: ImageDatabase
) {
    fun insertImage(imagePath: String): Long {
        val image = ImageEntity(storagePath = imagePath)
        return database.imageDao().insertImage(image)
    }

    fun getImagePath(id: Int) = database.imageDao().getImage(id).storagePath
}