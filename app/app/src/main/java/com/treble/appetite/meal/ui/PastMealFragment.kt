package com.treble.appetite.meal.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.treble.appetite.R
import kotlinx.android.synthetic.main.fragment_past_meal.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PastMealFragment : Fragment() {
    private val pastMealViewModel by viewModel<PastMealViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
            viewAdapter.setImages(it)
        })

        recyclerview_images.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}