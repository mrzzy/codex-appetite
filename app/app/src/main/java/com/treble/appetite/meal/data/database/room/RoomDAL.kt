package com.treble.appetite.meal.data.database.room

import com.treble.appetite.meal.data.database.MealDAL
import com.treble.appetite.meal.model.Meal
import java.util.*

class RoomDAL(private val database: MealDatabase) : MealDAL {
    override fun hasCurrentMeal(): Boolean = getCurrentMeal() != null

    override fun getCurrentMeal(): Meal? {
        val meal = database.mealDao().getCurrentMeal()
        return meal?.toMeal()
    }

    override fun getPreviousMeals(): List<Meal> = database.mealDao().getPreviousMeals().map { it.toMeal() }

    override fun addMeal(beforeImagePath: String): Long {
        val meal = MealEntity(0, null, beforeImagePath, null)
        return database.mealDao().insertMeal(meal)
    }

    override fun updateMeal(id: Long, afterImagePath: String) {
        database.mealDao().updateMeal(id, Date(), afterImagePath)
    }

    override fun setPortion(newPortion: Int) {
        database.portionDao().savePortion(PortionEntity(newPortion))
    }

    override fun getPortion(): Int? = database.portionDao().getPortion()?.portion
}