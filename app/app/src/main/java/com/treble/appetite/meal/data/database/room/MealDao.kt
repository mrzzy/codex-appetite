package com.treble.appetite.meal.data.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MealDao {
    @Insert
    fun insertMeal(meal: MealEntity): Long

    @Query("SELECT * FROM mealentity ORDER BY mealDate DESC LIMIT 1")
    fun getLastMeal(): MealEntity

    @Query("SELECT * FROM mealentity WHERE afterImagePath IS NOT NULL ORDER BY mealDate LIMIT 10")
    fun getPreviousMeals(): List<MealEntity>
}