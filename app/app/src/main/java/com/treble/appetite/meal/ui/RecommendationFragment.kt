package com.treble.appetite.meal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.treble.appetite.R
import com.treble.appetite.meal.data.MealRepository
import kotlinx.android.synthetic.main.fragment_recommendation.*
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recommendationViewModel.portionLiveData.observe(this, Observer {
            textview_meal_grams.text = it.toString()
        })
    }
}