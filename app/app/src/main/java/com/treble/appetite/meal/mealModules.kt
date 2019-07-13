package com.treble.appetite.meal

import androidx.room.Room
import com.treble.appetite.meal.data.MealRepository
import com.treble.appetite.meal.data.database.MealDAL
import com.treble.appetite.meal.data.database.room.MealDatabase
import com.treble.appetite.meal.data.database.room.RoomDAL
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.bind
import org.koin.dsl.module

val mealModules : Module = module {
    single {
        Room.databaseBuilder(androidApplication(), MealDatabase::class.java, "meal-db").build()
    }

    factory { RoomDAL(get()) } bind MealDAL::class

    single { MealRepository(get()) }
}