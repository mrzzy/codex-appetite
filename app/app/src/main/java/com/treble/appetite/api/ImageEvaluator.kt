package com.treble.appetite.api

interface ImageEvaluator {
    suspend fun evaluateImages(beforePath: String, afterPath: String): Int
}