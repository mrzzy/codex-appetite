package com.treble.appetite.api

import com.treble.appetite.http.DefaultWebservice
import com.treble.appetite.http.Webservice
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val apiModule : Module = module {
    factory { DefaultWebservice() } bind Webservice::class
}
