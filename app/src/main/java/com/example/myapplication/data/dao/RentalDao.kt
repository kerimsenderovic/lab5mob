package com.example.myapplication.data.dao

import androidx.room.*
import com.example.myapplication.data_model.Rental
import kotlinx.coroutines.flow.Flow

@Dao
interface RentalDao {
    @Query("SELECT * FROM rentals")
    fun getAll(): Flow<List<Rental>>

    @Insert
    suspend fun insert(rental: Rental)

    @Delete
    suspend fun delete(rental: Rental)

    @Query("DELETE FROM rentals")
    suspend fun deleteAll()  // Clears all rentals
}