package com.treble.appetite.http

import com.github.kittinunf.fuel.core.DataPart
import com.github.kittinunf.fuel.core.FileDataPart

data class ImagePart(
    val path: String,
    val name: String
) {
    fun toFileDataPart(): DataPart {
        return FileDataPart.from(path, name = name)
    }
}