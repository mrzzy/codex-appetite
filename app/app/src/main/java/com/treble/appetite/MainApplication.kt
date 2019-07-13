package com.treble.appetite

import android.app.Application
import com.treble.appetite.api.apiModule
import com.treble.appetite.meal.mealModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()

            androidContext(this@MainApplication)

            androidFileProperties()

            modules(listOf(apiModule, mealModules))
        }
    }
}
