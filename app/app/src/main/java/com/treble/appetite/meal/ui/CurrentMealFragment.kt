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
import kotlinx.android.synthetic.main.fragment_current_meal.*
import org.koin.android.ext.android.inject

class CurrentMealFragment : Fragment() {
    private val mealRepository: MealRepository by inject()
    private lateinit var currentMealViewModel: CurrentMealViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        currentMealViewModel = ViewModelProviders.of(this,
            MealViewModelFactory(mealRepository)
        ).get(CurrentMealViewModel::class.java)

        return inflater.inflate(R.layout.fragment_current_meal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentMealViewModel.imageBitmap.observe(this, Observer {
            imageview_current_meal.setImageBitmap(it)
        })
    }
}