package com.treble.appetite.meal.data

import com.treble.appetite.meal.data.database.MealDAL
import com.treble.appetite.meal.model.Meal

class MealRepository(
    private val mealDAL: MealDAL
) {
    fun getLastMeal(): Meal = mealDAL.getLastMeal()
    fun getPreviousMeals(): List<Meal> = mealDAL.getPreviousMeals()

    fun getRecommendedPortion(): Int {

    }
}