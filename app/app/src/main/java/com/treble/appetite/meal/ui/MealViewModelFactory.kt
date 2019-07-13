package com.treble.appetite.meal.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.treble.appetite.meal.data.MealRepository

class MealViewModelFactory(private val mealRepository: MealRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PastMealViewModel::class.java)) {
            PastMealViewModel(mealRepository) as T
        } else if (modelClass.isAssignableFrom(RecommendationViewModel::class.java)) {
            RecommendationViewModel(mealRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}