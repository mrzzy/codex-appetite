package com.treble.appetite.meal.data.database

import com.treble.appetite.meal.model.Meal

interface MealDAL {
    fun getLastMeal(): Meal
    fun getPreviousMeals(): List<Meal>
}