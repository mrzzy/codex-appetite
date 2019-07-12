package com.treble.appetite.meal.model

import java.util.*

data class Meal(
    var id: Int,
    var mealDate: Date?,
    var beforeImagePath: String,
    var afterImagePath: String?
)
