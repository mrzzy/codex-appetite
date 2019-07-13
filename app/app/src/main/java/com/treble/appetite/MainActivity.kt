package com.treble.appetite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.treble.appetite.meal.data.MealRepository
import com.treble.appetite.meal.ui.CurrentMealFragment
import com.treble.appetite.meal.ui.RecommendationFragment
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val mealRepository: MealRepository by inject()
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        uiScope.launch(Dispatchers.IO) {
            if (mealRepository.hasCurrentMeal()) {
                withContext(Dispatchers.Main) {
                    val currentMealFragment = CurrentMealFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.top_fragment, currentMealFragment)
                        .commit()
                }
            } else {
                withContext(Dispatchers.Main) {
                    val recommendationFragment = RecommendationFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.top_fragment, recommendationFragment)
                        .commit()
                }
            }
        }


        setContentView(R.layout.activity_main)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelJob.cancel()
    }
}
