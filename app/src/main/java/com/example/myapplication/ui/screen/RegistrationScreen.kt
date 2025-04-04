package com.example.myapplication.ui.screen

import android.util.Patterns
import android.widget.Toast
import com.example.myapplication.ui.components.InputField

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import com.example.myapplication.utils.isValidEmail
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import com.example.myapplication.ui.components.InputField

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.Icon

@Composable
fun CarRentalRegistrationScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize().background(Color(0xFF1B1B1B)),
        contentAlignment = Alignment.BottomCenter
    ) {

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.car22),
            contentDescription = "Car Rental Background",
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier.fillMaxSize().offset(y = 200.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(text = "WELCOME TO CAR RENTAL!\n BOOK YOUR RIDE NOW.", color = Color.White, fontFamily = FontFamily.Serif)
        }
        Column(
            modifier = Modifier
                .padding(all = 30.dp).height(280.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Scroll up to see all the fields.", color = Color.White)

            InputField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = if (isValidEmail(it)) "" else "Please enter a valid email address!"
                },
                label = "Email", icon = Icons.Default.Email,
                errorMessage = emailError
            )
            InputField(value = firstName, onValueChange = { firstName = it }, label = "First Name", icon = Icons.Default.AccountCircle)
            InputField(value = lastName, onValueChange = { lastName = it }, label = "Last Name", icon = Icons.Default.AccountCircle)

            // Phone Number field without icon
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number", color = Color.White) },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    focusedIndicatorColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = Color.White) },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color.White) },
                trailingIcon = {
                    val icon = if (passwordVisible) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = icon, contentDescription = "Toggle password visibility", tint = Color.Red)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedTextColor = Color.White
                )
            )

            Button(
                onClick = {
                    Toast.makeText(context, "Registration Successful!", Toast.LENGTH_SHORT).show()
                },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007f9c))
            ) {
                Text(text = "Register", color = Color.White)
            }
        }
    }
}



