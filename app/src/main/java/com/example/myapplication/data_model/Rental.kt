package com.example.myapplication.data_model

import androidx.room.*

@Entity(
    tableName = "rentals",
    foreignKeys = [
        ForeignKey(entity = Customer::class, parentColumns = ["id"], childColumns = ["customerId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Car::class, parentColumns = ["id"], childColumns = ["carId"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("customerId"), Index("carId")]
)
data class Rental(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val customerId: Int,
    val carId: Int,
    val rentalDate: String,
    val returnDate: String
)