package com.treble.appetite.meal.data

import com.treble.appetite.meal.data.database.MealDAL
import com.treble.appetite.meal.model.Meal

class MealRepository(
    private val mealDAL: MealDAL
) {
    fun getCurrentMeal(): Meal? = mealDAL.getCurrentMeal()
    fun getPreviousMeals(): List<Meal> = mealDAL.getPreviousMeals()
    fun getMeal(id: Long): Meal? = mealDAL.getMeal(id)
    fun addMeal(beforeImagePath: String): Long = mealDAL.addMeal(beforeImagePath)
    fun updateMeal(id: Long, afterImagePath: String) = mealDAL.updateMeal(id, afterImagePath)

    fun getRecommendedPortion(): Int? = mealDAL.getPortion()
    fun setRecommendedPortion(portion: Int) = mealDAL.setPortion(portion)

    fun hasCurrentMeal(): Boolean = mealDAL.hasCurrentMeal()
}