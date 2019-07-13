package com.treble.appetite.http

import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module


val httpModule : Module = module {
    factory { DefaultWebservice() } bind Webservice::class
}