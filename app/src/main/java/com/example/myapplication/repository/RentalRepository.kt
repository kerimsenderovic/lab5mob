package com.example.myapplication.repository



import com.example.myapplication.data.dao.RentalDao
import com.example.myapplication.data_model.Rental
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RentalRepository @Inject constructor(
    private val rentalDao: RentalDao
) {
    // Fetch all rentals from the database
    fun getAllRentals(): Flow<List<Rental>> = rentalDao.getAll()

    // Insert a new rental into the database
    suspend fun insertRental(rental: Rental) = rentalDao.insert(rental)

    // Delete a rental from the database
    suspend fun deleteRental(rental: Rental) = rentalDao.delete(rental)

    // Optionally clear all rentals from the database (if you need this functionality)
    suspend fun clearRentals() {
        rentalDao.deleteAll()  // You'll need to create a deleteAll() method in your DAO
    }
}
