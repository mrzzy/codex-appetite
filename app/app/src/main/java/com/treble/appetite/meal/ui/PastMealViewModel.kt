package com.treble.appetite.meal.ui

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.treble.appetite.meal.data.MealRepository
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.File


class PastMealViewModel(
    private val mealRepository: MealRepository
) : ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val imagesLiveData = MutableLiveData<List<Bitmap>>()

    private fun createBitmap(filePath: String): Bitmap {
        val image = File(filePath)
        val bmOptions = BitmapFactory.Options()
        return BitmapFactory.decodeFile(image.absolutePath, bmOptions)
    }

    init {
        uiScope.launch(Dispatchers.IO) {
            val imageBitmaps = mealRepository.getPreviousMeals().map {
                createBitmap(it.beforeImagePath)
            }

            imagesLiveData.postValue(imageBitmaps)
        }
    }


    override fun onCleared() {
        super.onCleared()

        // Clear any coroutines started by the ViewModel
        viewModelJob.cancel()
    }
}