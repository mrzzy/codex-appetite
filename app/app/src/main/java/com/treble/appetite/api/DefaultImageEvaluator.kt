package com.treble.appetite.api

import com.treble.appetite.http.ImagePart
import com.treble.appetite.http.Webservice

class DefaultImageEvaluator(
    val webservice: Webservice,
    val apiEndpoint: String
) : ImageEvaluator {
    override suspend fun evaluateImages(beforePath: String, afterPath: String): Int {
        val images = listOf<ImagePart>(
            ImagePart(beforePath, "before"),
            ImagePart(afterPath, "after")
        )

        return webservice.postImages(apiEndpoint, images).toInt()
    }
}