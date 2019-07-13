package com.treble.appetite.meal.data.database

import com.treble.appetite.meal.model.Meal

interface MealDAL {
    fun getCurrentMeal(): Meal?
    fun getMeal(id: Long): Meal?
    fun hasCurrentMeal(): Boolean
    fun getPreviousMeals(): List<Meal>
    fun addMeal(beforeImagePath: String): Long
    fun updateMeal(id: Long, afterImagePath: String)

    fun setPortion(newPortion: Int)
    fun getPortion(): Int?
}