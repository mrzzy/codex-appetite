package com.treble.appetite.meal.data.database.room

import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.treble.appetite.meal.model.Meal
import java.util.*

@Entity
data class MealEntity(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @Nullable
    var mealDate: Date?,
    var beforeImagePath: String,
    @Nullable
    var afterImagePath: String?
) {
    fun toMeal(): Meal = Meal(id, mealDate, beforeImagePath, afterImagePath)
}