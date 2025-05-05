package com.example.myapplication

import android.app.Application
import android.util.Log // <-- Import Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first // <-- Import first from kotlinx.coroutines.flow
import kotlinx.coroutines.launch
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.data_model.Car
import com.example.myapplication.data_model.Customer
import com.example.myapplication.data_model.Rental
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class CarRentalApp : Application() {

    @Inject lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()

        // Inject Hilt dependencies
        // Sample data injection
        val car1 = Car(id = 1, brand = "Toyota", model = "Corolla", year = 2020, isAvailable = true)
        val customer1 = Customer(id = 1, name = "John Doe", email = "johndoe@example.com", phoneNumber = "1234567890")
        val rental1 = Rental(id = 1, carId = 1, customerId = 1, rentalDate = "2025-05-05", returnDate = "2025-05-10")

        // Launch a coroutine using a CoroutineScope and Dispatchers.Main
        CoroutineScope(Dispatchers.Main).launch {
            appDatabase.carDao().insert(car1)
            appDatabase.customerDao().insert(customer1)
            appDatabase.rentalDao().insert(rental1)

            // Fetch all cars after inserting and log the result
            val cars = appDatabase.carDao().getAll().first() // .first() gets the first emitted list
            Log.d("DatabaseTest", "Cars in DB: $cars") // Log the cars in DB
        }
    }
}
