package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data_model.Rental
import com.example.myapplication.repository.RentalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RentalViewModel @Inject constructor(
    private val rentalRepository: RentalRepository
) : ViewModel() {

    private val _rentals = MutableStateFlow<List<Rental>>(emptyList())
    val rentals: StateFlow<List<Rental>> = _rentals

    init {
        viewModelScope.launch {
            rentalRepository.getAllRentals().collect { rentalList ->
                _rentals.value = rentalList
            }
        }
    }

    fun addRental(rental: Rental) {
        viewModelScope.launch {
            rentalRepository.insertRental(rental)
            _rentals.update { it + rental }
        }
    }

    fun removeRental(rental: Rental) {
        viewModelScope.launch {
            rentalRepository.deleteRental(rental)
            _rentals.update { it.filterNot { r -> r.id == rental.id } }
        }
    }

    fun clearRentals() {
        viewModelScope.launch {
            rentalRepository.clearRentals()
            _rentals.value = emptyList()
        }
    }
}
