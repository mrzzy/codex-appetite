package com.treble.appetite.camera.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    var storagePath: String
)