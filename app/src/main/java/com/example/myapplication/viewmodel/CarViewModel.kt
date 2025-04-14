package com.example.myapplication.viewmodel



import androidx.lifecycle.ViewModel
import com.example.myapplication.model.Car
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CarViewModel : ViewModel() {

    private val _cars = MutableStateFlow<List<Car>>(emptyList())
    val cars: StateFlow<List<Car>> = _cars

    val carTags = mutableSetOf("Electric", "SUV", "Diesel")
    val carMap = mutableMapOf(1 to "Audi", 2 to "BMW")

    fun addCar(car: Car) {
        _cars.update { it + car }
    }

    fun removeCar(car: Car) {
        _cars.update { it.filterNot { c -> c.id == car.id } }
    }

    fun clearCars() {
        _cars.value = emptyList()
    }
}
