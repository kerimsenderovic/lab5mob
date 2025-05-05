package com.example.myapplication.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.data_model.*
import com.example.myapplication.data.dao.*
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext  // <-- Correct import here
import javax.inject.Singleton

@Database(entities = [Customer::class, Car::class, Rental::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun customerDao(): CustomerDao
    abstract fun carDao(): CarDao
    abstract fun rentalDao(): RentalDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "car_rental_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
