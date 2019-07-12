package com.treble.appetite.meal.data.database.room

import com.treble.appetite.meal.data.database.MealDAL
import com.treble.appetite.meal.model.Meal

class RoomDAL(private val database: MealDatabase) : MealDAL {
    override fun getLastMeal(): Meal = database.mealDao().getLastMeal().toMeal()

    override fun getPreviousMeals(): List<Meal> = database.mealDao().getPreviousMeals().map { it.toMeal() }
}