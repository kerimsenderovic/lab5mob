package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.data_model.Car
import com.example.myapplication.data_model.Customer
import com.example.myapplication.data_model.Rental
import com.example.myapplication.database.AppDatabase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var appDatabase: AppDatabase // Inject the database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Call the method to test the database
        testDatabase()
    }

    private fun testDatabase() {
        // Sample data to insert
        val car1 = Car(id = 1, brand = "Toyota", model = "Corolla", year = 2020, isAvailable = true)
        val customer1 = Customer(id = 1, name = "John Doe", email = "johndoe@example.com", phoneNumber = "1234567890")
        val rental1 = Rental(id = 1, carId = 1, customerId = 1, rentalDate = "2025-05-05", returnDate = "2025-05-10")

        // Launch a coroutine on IO thread to perform DB operations
        lifecycleScope.launch(Dispatchers.IO) {
            // Insert data into the database
            appDatabase.carDao().insert(car1)
            appDatabase.customerDao().insert(customer1)
            appDatabase.rentalDao().insert(rental1)

            // Fetch all cars from the database
            val cars = appDatabase.carDao().getAll().first() // Get the first emitted list of cars
            Log.d("DatabaseTest", "Cars in DB: $cars") // Log the list of cars in DB

            // Optionally, fetch other data like customer and rental info
            val customers = appDatabase.customerDao().getAll().first() // Get all customers
            Log.d("DatabaseTest", "Customers in DB: $customers")

            val rentals = appDatabase.rentalDao().getAll().first() // Get all rentals
            Log.d("DatabaseTest", "Rentals in DB: $rentals")
        }
    }
}
