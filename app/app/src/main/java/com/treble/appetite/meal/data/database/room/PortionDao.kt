package com.treble.appetite.meal.data.database.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PortionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePortion(portion: Int)

    @Query("SELECT * FROM portionentity")
    fun getPortion(): PortionEntity
}