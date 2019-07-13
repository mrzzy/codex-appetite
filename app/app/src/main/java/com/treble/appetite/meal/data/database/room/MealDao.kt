package com.treble.appetite.meal.data.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import java.util.*

@Dao
interface MealDao {
    @Insert
    fun insertMeal(meal: MealEntity): Long

    @Query("SELECT * FROM mealentity ORDER BY mealDate DESC LIMIT 1")
    fun getLastMeal(): MealEntity

    @Query("SELECT * FROM mealentity WHERE afterImagePath IS NOT NULL ORDER BY mealDate LIMIT 10")
    fun getPreviousMeals(): List<MealEntity>

    @Query("UPDATE mealentity SET mealDate = :mealDate, afterImagePath = :afterImagePath WHERE id = :id")
    fun updateMeal(id: Long, mealDate: Date, afterImagePath: String)
}