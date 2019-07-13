package com.treble.appetite.meal.data.database.room

import com.treble.appetite.meal.data.database.MealDAL
import com.treble.appetite.meal.model.Meal
import java.util.*

class RoomDAL(private val database: MealDatabase) : MealDAL {
    override fun getLastMeal(): Meal = database.mealDao().getLastMeal().toMeal()

    override fun getPreviousMeals(): List<Meal> = database.mealDao().getPreviousMeals().map { it.toMeal() }

    override fun addMeal(beforeImagePath: String): Long {
        val meal = MealEntity(0, null, beforeImagePath)
        return database.mealDao().insertMeal(meal)
    }

    override fun updateMeal(id: Long, afterImagePath: String) {
        database.mealDao().updateMeal(id, Date(), afterImagePath)
    }
}