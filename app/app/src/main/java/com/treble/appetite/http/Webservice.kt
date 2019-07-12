package com.treble.appetite.http

interface Webservice {
    suspend fun postImages(url: String, images: List<ImagePart>): String
}