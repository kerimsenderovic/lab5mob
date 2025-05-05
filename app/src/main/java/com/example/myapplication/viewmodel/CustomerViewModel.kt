package com.example.myapplication.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data_model.Customer
import com.example.myapplication.repository.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerViewModel @Inject constructor(
    private val customerRepository: CustomerRepository
) : ViewModel() {

    private val _customers = MutableStateFlow<List<Customer>>(emptyList())
    val customers: StateFlow<List<Customer>> = _customers

    init {
        viewModelScope.launch {
            customerRepository.getAllCustomers().collect { customerList ->
                _customers.value = customerList
            }
        }
    }

    fun addCustomer(customer: Customer) {
        viewModelScope.launch {
            customerRepository.insertCustomer(customer)
            _customers.update { it + customer }
        }
    }

    fun removeCustomer(customer: Customer) {
        viewModelScope.launch {
            customerRepository.deleteCustomer(customer)
            _customers.update { it.filterNot { c -> c.id == customer.id } }
        }
    }

    fun clearCustomers() {
        viewModelScope.launch {
            customerRepository.clearCustomers()
            _customers.value = emptyList()
        }
    }
}