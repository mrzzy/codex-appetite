package com.treble.appetite.meal.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.treble.appetite.R
import com.treble.appetite.meal.data.MealRepository
import kotlinx.android.synthetic.main.fragment_past_meal.*
import org.koin.android.ext.android.inject

class PastMealFragment : Fragment() {
    private val mealRepository: MealRepository by inject()
    private lateinit var pastMealViewModel: PastMealViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        pastMealViewModel = ViewModelProviders.of(this,
            MealViewModelFactory(mealRepository)
        ).get(PastMealViewModel::class.java)

        return inflater.inflate(R.layout.fragment_past_meal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Prepare for feed RecyclerView
        val viewManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        // Start with no elements, update later when the data is ready
        val viewAdapter = MealFeedAdapter(listOf())

        // Set an observer on the Feed LiveData for when the data is ready
        pastMealViewModel.imagesLiveData.observe(this, Observer<List<Bitmap>> {
            if (it.count() == 0) {
                textview_no_past_meals.visibility = View.VISIBLE
            } else {
                viewAdapter.setImages(it)
            }
        })

        recyclerview_images.apply {
            layoutManager = viewManager
            adapter = viewAdapter
            isNestedScrollingEnabled = true
        }
    }
}