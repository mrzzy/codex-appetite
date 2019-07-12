package com.treble.appetite.camera

import androidx.room.Room
import com.treble.appetite.camera.data.ImageDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

val cameraModule : Module = module {
    single {
        Room.databaseBuilder(androidApplication(), ImageDatabase::class.java, "image-db").build()
    }
}