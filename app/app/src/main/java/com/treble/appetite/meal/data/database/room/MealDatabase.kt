package com.treble.appetite.meal.data.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [MealEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class MealDatabase : RoomDatabase() {
    abstract fun mealDao(): MealDao
}