package com.treble.appetite.meal.data.database

import com.treble.appetite.meal.model.Meal

interface MealDAL {
    fun getLastMeal(): Meal
    fun getPreviousMeals(): List<Meal>
    fun addMeal(beforeImagePath: String): Long
    fun updateMeal(id: Long, afterImagePath: String)

    fun setPortion(newPortion: Int)
    fun getPortion(): Int
}