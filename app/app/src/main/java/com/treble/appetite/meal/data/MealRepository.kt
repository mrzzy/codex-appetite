package com.treble.appetite.meal.data

import com.treble.appetite.meal.data.database.MealDAL
import com.treble.appetite.meal.model.Meal

class MealRepository(
    private val mealDAL: MealDAL
) {
    fun getLastMeal(): Meal = mealDAL.getLastMeal()
    fun getPreviousMeals(): List<Meal> = mealDAL.getPreviousMeals()
    fun addMeal(beforeImagePath: String): Long = mealDAL.addMeal(beforeImagePath)
    fun updateMeal(id: Long, afterImagePath: String) = mealDAL.updateMeal(id, afterImagePath)

    fun getRecommendedPortion(): Int = mealDAL.getPortion()
    fun setRecommendedPortion(portion: Int) = mealDAL.setPortion(portion)
}