package com.example.myapplication.repository



import com.example.myapplication.data.dao.CustomerDao
import com.example.myapplication.data_model.Customer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CustomerRepository @Inject constructor(
    private val customerDao: CustomerDao
) {
    // Fetch all customers from the database
    fun getAllCustomers(): Flow<List<Customer>> = customerDao.getAll()

    // Insert a new customer into the database
    suspend fun insertCustomer(customer: Customer) = customerDao.insert(customer)

    // Delete a customer from the database
    suspend fun deleteCustomer(customer: Customer) = customerDao.delete(customer)

    // Optionally clear all customers from the database (if you need this functionality)
    suspend fun clearCustomers() {
        customerDao.deleteAll()  // You'll need to create a deleteAll() method in your DAO
    }
}
