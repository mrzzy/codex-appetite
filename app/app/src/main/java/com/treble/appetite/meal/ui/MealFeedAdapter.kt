package com.treble.appetite.meal.ui

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.treble.appetite.R

class MealFeedAdapter(private var images: List<Bitmap>) : RecyclerView.Adapter<MealFeedAdapter.MealViewHolder>() {
    class MealViewHolder(pastMealView: ViewGroup) : RecyclerView.ViewHolder(pastMealView) {
        val imageView: ImageView = pastMealView.findViewById(R.id.imageview_meal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val pastMealView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_meal_item, parent, false) as ViewGroup

        return MealViewHolder(pastMealView)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val mealBitmap: Bitmap = images[position]

        holder.imageView.setImageBitmap(mealBitmap)
    }

    override fun getItemCount(): Int = images.count()

    fun setImages(newImages: List<Bitmap>) {
        images = newImages
        notifyDataSetChanged()
    }
}