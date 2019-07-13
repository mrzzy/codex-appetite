package com.treble.appetite.api

import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val apiModule : Module = module {
    factory { DefaultImageEvaluator(get(), "http://35.198.252.96:8080") } bind ImageEvaluator::class
}
