package com.treble.appetite.meal.data.database.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PortionEntity(
    @PrimaryKey var portion: Int
)