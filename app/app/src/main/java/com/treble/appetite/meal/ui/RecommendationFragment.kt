package com.treble.appetite.meal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.treble.appetite.R
import com.treble.appetite.meal.data.MealRepository
import org.koin.android.ext.android.inject

class RecommendationFragment : Fragment() {
    private val mealRepository: MealRepository by inject()
    private lateinit var recommendationViewModel: RecommendationViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        recommendationViewModel = ViewModelProviders.of(this,
            MealViewModelFactory(mealRepository)
        ).get(RecommendationViewModel::class.java)

        return inflater.inflate(R.layout.fragment_recommendation , container, false)
    }
}