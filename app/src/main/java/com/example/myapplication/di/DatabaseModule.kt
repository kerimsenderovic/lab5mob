package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.dao.*
import com.example.myapplication.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    // Provide the AppDatabase using the singleton method
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    // Provide DAOs
    @Provides
    fun provideCustomerDao(db: AppDatabase): CustomerDao = db.customerDao()

    @Provides
    fun provideCarDao(db: AppDatabase): CarDao = db.carDao()

    @Provides
    fun provideRentalDao(db: AppDatabase): RentalDao = db.rentalDao()
}
