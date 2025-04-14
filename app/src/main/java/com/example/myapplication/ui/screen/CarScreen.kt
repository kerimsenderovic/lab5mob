package com.example.myapplication.ui.screen
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.viewmodel.CarViewModel
import com.example.myapplication.model.Car

@Composable
fun CarScreen(carViewModel: CarViewModel = viewModel()) {
    val carList by carViewModel.cars.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text("🚗 Car List:", style = MaterialTheme.typography.titleLarge)

        LazyColumn {
            items(carList) { car ->
                Text(text = "• ${car.name}", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row {
            Button(onClick = {
                val newId = (carList.size + 1)
                carViewModel.addCar(Car(newId, "Car $newId"))
            }) {
                Text("Add Car")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = {
                carList.lastOrNull()?.let { carViewModel.removeCar(it) }
            }) {
                Text("Remove Last")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { carViewModel.clearCars() }) {
                Text("Clear")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Tags: ${carViewModel.carTags.joinToString()}")
        Text("Map: ${carViewModel.carMap}")
    }
}
