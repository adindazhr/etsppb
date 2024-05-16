package com.example.mylogin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import java.time.LocalDate

@Composable
fun HomeScreen(navController: NavHostController) {
    val cities = listOf("Surabaya (SUB)", "Bali/Denpasar (DPS)", "Jakarta (CGK)")
    val (departureExpanded, setDepartureExpanded) = remember { mutableStateOf(false) }
    val (destinationExpanded, setDestinationExpanded) = remember { mutableStateOf(false) }
    val (departureCity, setDepartureCity) = remember { mutableStateOf(cities[0]) }
    val (destinationCity, setDestinationCity) = remember { mutableStateOf(cities[0]) }
    val departureDate = remember { mutableStateOf(TextFieldValue()) }
    val returnDate = remember { mutableStateOf(TextFieldValue()) }
    var adults by remember { mutableStateOf(1) }
    var kids by remember { mutableStateOf(0) }
    var cabinClass by remember { mutableStateOf("Economy") }
    var flightType by remember { mutableStateOf(FlightType.OneWay) }
    var promoCode by remember { mutableStateOf("") }
    val brush = Brush.horizontalGradient(listOf(Color(0xFF303F9F), Color(0xFF26A69A)))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = brush)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Spacer(modifier = Modifier.height(48.dp))

            Text(text = "Book a Flight", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        // Rounded box for the form
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter) // Align to the bottom of the screen
                .padding(vertical = 16.dp)
                .padding(horizontal = 16.dp)
                .fillMaxHeight(0.88f) // Fill 2/3 of the screen height
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) // Rounded corners
                .background(color = Color.White) // White background
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                FlightTypeSelection(
                    modifier = Modifier.fillMaxWidth(),
                    selectedFlightType = flightType,
                    onFlightTypeChange = { newFlightType ->
                        flightType = newFlightType
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box {
                    OutlinedTextField(
                        value = departureCity,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Departure City") },
                        trailingIcon = {
                            IconButton(onClick = { setDepartureExpanded(true) }) {
                                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                            }
                        },
                        modifier = Modifier.fillMaxWidth().clickable { setDepartureExpanded(true) }
                    )
                    DropdownMenu(
                        expanded = departureExpanded,
                        onDismissRequest = { setDepartureExpanded(false) },
                        modifier = Modifier.fillMaxWidth(),
                        properties = PopupProperties(focusable = true)
                    ) {
                        cities.forEach { city ->
                            DropdownMenuItem(
                                onClick = {
                                    setDepartureCity(city)
                                    setDepartureExpanded(false)
                                },
                                text = { Text(text = city) }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Box {
                    OutlinedTextField(
                        value = destinationCity,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Destination City") },
                        trailingIcon = {
                            IconButton(onClick = { setDestinationExpanded(true) }) {
                                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                            }
                        },
                        modifier = Modifier.fillMaxWidth().clickable { setDestinationExpanded(true) }
                    )
                    DropdownMenu(
                        expanded = destinationExpanded,
                        onDismissRequest = { setDestinationExpanded(false) },
                        modifier = Modifier.fillMaxWidth(),
                        properties = PopupProperties(focusable = true)
                    ) {
                        cities.forEach { city ->
                            DropdownMenuItem(
                                onClick = {
                                    setDestinationCity(city)
                                    setDestinationExpanded(false)
                                },
                                text = { Text(text = city) }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = departureDate.value,
                    onValueChange = { departureDate.value = it },
                    label = { Text(text = "Departure Date") },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                // Handle icon click action, such as opening a date picker dialog
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = "Select Date"
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                PassengerAndCabinInput(
                    modifier = Modifier.fillMaxWidth(),
                    onPassengerChange = { newAdults, newKids ->
                        adults = newAdults
                        kids = newKids
                    },
                    onCabinClassChange = { cabinClass = it }
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = promoCode,
                    onValueChange = { promoCode = it },
                    label = { Text("Promo Code") },
                    placeholder = { Text("Enter your promo code") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = { navController.navigate("flight") }) {
                    Text(text = "Book Flight")
                }
            }
        }
    }
}

@Composable
fun PassengerAndCabinInput(
    modifier: Modifier = Modifier,
    onPassengerChange: (Int, Int) -> Unit,
    onCabinClassChange: (String) -> Unit
) {
    var adults by remember { mutableStateOf(1) }
    var kids by remember { mutableStateOf(0) }
    val cabinClasses = listOf("Economy", "Premium Economy", "Business", "First")

    Column(modifier = modifier) {
        Row(modifier = modifier){
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Adults: $adults")
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { adults++ }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Adult")
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { if (adults > 1) adults-- }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Remove Adult")
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Kids: $kids")
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { kids++ }) {
                    Icon(Icons.Default.Add, contentDescription = "Add Kid")
                }
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(onClick = { if (kids > 0) kids-- }) {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Remove Kid")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown for cabin class selection
        var expanded by remember { mutableStateOf(false) }
        var selectedCabinClass by remember { mutableStateOf(cabinClasses[0]) }

        Box {
            OutlinedTextField(
                value = selectedCabinClass,
                onValueChange = { },
                label = { Text("Cabin Class") },
                trailingIcon = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = "Dropdown")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { expanded = true }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                cabinClasses.forEach { cabinClass ->
                    DropdownMenuItem(
                        onClick = {
                            selectedCabinClass = cabinClass
                            onCabinClassChange(cabinClass)
                            expanded = false
                        },
                        text = { Text(text = cabinClass) }
                    )
                }
            }
        }
    }
}

@Composable
fun FlightTypeSelection(
    modifier: Modifier = Modifier,
    selectedFlightType: FlightType,
    onFlightTypeChange: (FlightType) -> Unit
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Text("Flight Type:")
        Spacer(modifier = Modifier.width(8.dp))
        RadioButton(
            selected = selectedFlightType == FlightType.OneWay,
            onClick = { onFlightTypeChange(FlightType.OneWay) }
        )
        Text("One Way")
        Spacer(modifier = Modifier.width(16.dp))
        RadioButton(
            selected = selectedFlightType == FlightType.Return,
            onClick = { onFlightTypeChange(FlightType.Return) }
        )
        Text("Return")
    }
}

enum class FlightType {
    OneWay, Return
}
