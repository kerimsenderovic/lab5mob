package com.example.myapplication.data.dao



import androidx.room.*
import com.example.myapplication.data_model.Car
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {

    @Query("SELECT * FROM cars")
    fun getAll(): Flow<List<Car>>

    @Insert
    suspend fun insert(car: Car)

    @Update
    suspend fun update(car: Car)

    @Delete
    suspend fun delete(car: Car)

    @Query("DELETE FROM cars")
    suspend fun deleteAll()  // This method clears all cars
}