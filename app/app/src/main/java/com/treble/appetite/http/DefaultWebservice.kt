package com.treble.appetite.http

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Method
import com.github.kittinunf.fuel.coroutines.awaitStringResponseResult

class DefaultWebservice : Webservice {
    override suspend fun postImages(url: String, images: List<ImagePart>): String {
        val upload = Fuel.upload(url, method = Method.POST)
        images.forEach {
            upload.add(it.toFileDataPart())
        }

        val (_, _, result) = upload.awaitStringResponseResult()

        return result.get()
    }
}