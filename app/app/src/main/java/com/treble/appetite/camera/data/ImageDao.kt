package com.treble.appetite.camera.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ImageDao {
    @Insert
    fun insertImage(image: ImageEntity): Long

    @Query("SELECT * FROM imageentity WHERE id = :id")
    fun getImage(id: Int): ImageEntity
}