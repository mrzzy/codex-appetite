package com.treble.appetite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.treble.appetite.camera.CameraActivity
import com.treble.appetite.meal.data.MealRepository
import com.treble.appetite.meal.ui.CurrentMealFragment
import com.treble.appetite.meal.ui.RecommendationFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val mealRepository: MealRepository by inject()
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val REQUEST_CODE_NEW_MEAL = 0
    private val REQUEST_CODE_FINISH_MEAL = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        uiScope.launch(Dispatchers.IO) {
            if (mealRepository.hasCurrentMeal()) {
                withContext(Dispatchers.Main) {
                    val currentMealFragment = CurrentMealFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.top_fragment, currentMealFragment)
                        .commit()

                    fab.setOnClickListener {
                        val intent = Intent(baseContext, CameraActivity::class.java)
                        startActivityForResult(intent, REQUEST_CODE_FINISH_MEAL)
                    }
                }
            } else {
                withContext(Dispatchers.Main) {
                    val recommendationFragment = RecommendationFragment()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.top_fragment, recommendationFragment)
                        .commit()

                    fab.setOnClickListener {
                        val intent = Intent(baseContext, CameraActivity::class.java)
                        startActivityForResult(intent, REQUEST_CODE_NEW_MEAL)
                    }
                }
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
                } else {
                    val currentMeal = mealRepository.getCurrentMeal()
                    mealRepository.updateMeal(currentMeal!!.id, filePath)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModelJob.cancel()
    }
}
