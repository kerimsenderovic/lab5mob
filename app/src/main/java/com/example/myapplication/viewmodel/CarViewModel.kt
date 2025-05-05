package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data_model.Car
import com.example.myapplication.repository.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(
    private val carRepository: CarRepository
) : ViewModel() {


    private val _cars = MutableStateFlow<List<Car>>(emptyList())
    val cars: StateFlow<List<Car>> = _cars


    init {
        viewModelScope.launch {
            carRepository.getAllCars().collect { carList ->
                _cars.value = carList
            }
        }
    }


    fun addCar(car: Car) {
        viewModelScope.launch {
            carRepository.insertCar(car)
            _cars.update { it + car }
        }
    }


    fun removeCar(car: Car) {
        viewModelScope.launch {
            carRepository.deleteCar(car)
            _cars.update { it.filterNot { c -> c.id == car.id } }
        }
    }


    fun clearCars() {
        viewModelScope.launch {
            carRepository.clearCars()
            _cars.value = emptyList()
        }
    }
}
