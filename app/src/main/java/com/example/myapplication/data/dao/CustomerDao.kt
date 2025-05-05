package com.example.myapplication.data.dao

import androidx.room.*
import com.example.myapplication.data_model.Customer
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @Query("SELECT * FROM customers")
    fun getAll(): Flow<List<Customer>>

    @Insert
    suspend fun insert(customer: Customer)

    @Delete
    suspend fun delete(customer: Customer)

    @Query("DELETE FROM customers")
    suspend fun deleteAll()  // Clears all customers
}