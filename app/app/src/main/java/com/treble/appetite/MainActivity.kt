package com.treble.appetite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.treble.appetite.api.ImageEvaluator
import com.treble.appetite.camera.CameraActivity
import com.treble.appetite.http.ImagePart
import com.treble.appetite.http.Webservice
import com.treble.appetite.meal.data.MealRepository
import com.treble.appetite.meal.model.Meal
import com.treble.appetite.meal.ui.CurrentMealFragment
import com.treble.appetite.meal.ui.RecommendationFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val mealRepository: MealRepository by inject()
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val imageEvaluator: ImageEvaluator by inject()

    private val REQUEST_CODE_NEW_MEAL = 0
    private val REQUEST_CODE_FINISH_MEAL = 1

    private fun showCurrentMeal() {
        val currentMealFragment = CurrentMealFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.top_fragment, currentMealFragment)
            .commit()

        fab.setOnClickListener {
            val intent = Intent(baseContext, CameraActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_FINISH_MEAL)
        }
    }

    private fun showRecommendation() {
        val recommendationFragment = RecommendationFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.top_fragment, recommendationFragment)
            .commit()

        fab.setOnClickListener {
            val intent = Intent(baseContext, CameraActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_NEW_MEAL)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        uiScope.launch(Dispatchers.IO) {
            if (mealRepository.hasCurrentMeal()) {
                withContext(Dispatchers.Main) { showCurrentMeal() }
            } else {
                withContext(Dispatchers.Main) { showRecommendation() }
            }
        }

        setContentView(R.layout.activity_main)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((requestCode == REQUEST_CODE_NEW_MEAL || requestCode == REQUEST_CODE_FINISH_MEAL)
            && data != null && data.hasExtra(CameraActivity.EXTRA_IMAGE_ID)) {
            val filePath = data.getStringExtra(CameraActivity.EXTRA_IMAGE_ID)

            uiScope.launch(Dispatchers.IO) {
                if (requestCode == REQUEST_CODE_NEW_MEAL) {
                    mealRepository.addMeal(filePath)
                    withContext(Dispatchers.Main) { showCurrentMeal() }
                } else {
                    val currentMeal = mealRepository.getCurrentMeal()
                    mealRepository.updateMeal(currentMeal!!.id, filePath)

                    val finishedMeal = mealRepository.getMeal(currentMeal.id)!!
                    imageEvaluator.evaluateImages(
                        finishedMeal.beforeImagePath,
                        finishedMeal.afterImagePath!!
                    )

                    withContext(Dispatchers.Main) { showRecommendation() }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelJob.cancel()
    }
}
