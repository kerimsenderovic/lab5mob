package com.example.myapplication.repository

import com.example.myapplication.data.dao.CarDao
import com.example.myapplication.data_model.Car
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CarRepository @Inject constructor(
    private val carDao: CarDao
) {
    // Fetch all cars from the database
    fun getAllCars(): Flow<List<Car>> = carDao.getAll()

    // Insert a new car into the database
    suspend fun insertCar(car: Car) = carDao.insert(car)

    // Delete a car from the database
    suspend fun deleteCar(car: Car) = carDao.delete(car)

    // Optionally clear all cars from the database (if you need this functionality)
    suspend fun clearCars() {
        carDao.deleteAll()  // You'll need to create a deleteAll() method in your DAO
    }
}