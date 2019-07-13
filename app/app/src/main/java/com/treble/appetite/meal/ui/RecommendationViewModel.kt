package com.treble.appetite.meal.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.treble.appetite.meal.data.MealRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RecommendationViewModel(
    mealRepository: MealRepository
) : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val portionLiveData = MutableLiveData<Int>()

    init {
        uiScope.launch(Dispatchers.IO) {
            portionLiveData.postValue(mealRepository.getRecommendedPortion())
        }
    }

    override fun onCleared() {
        super.onCleared()

        // Clear any coroutines started by the ViewModel
        viewModelJob.cancel()
    }
}