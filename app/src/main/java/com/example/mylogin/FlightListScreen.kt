package com.example.mylogin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.window.PopupProperties
import java.time.LocalDate
import java.time.format.TextStyle


@Composable
fun FlightListScreen() {
    val flights = listOf(
        Flight("GA123", "Surabaya", "Bali", "Garuda Indonesia", "10:00", "12:00", 1500000, "4:30"),
        Flight("GA456", "Surabaya", "Bali", "Garuda Indonesia", "14:00", "16:00", 1200000, "2:30"),
        Flight("GA789", "Surabaya", "Bali", "Garuda Indonesia", "18:00", "20:00", 1300000, "3:30"),
        Flight("GA101", "Jakarta", "Bali", "Garuda Indonesia", "8:00", "10:30", 1800000, "2:30"),
        Flight("GA202", "Jakarta", "Bali", "Garuda Indonesia", "12:00", "14:30", 1700000, "2:30"),
        Flight("GA303", "Jakarta", "Bali", "Garuda Indonesia", "16:00", "18:30", 1600000, "2:30")
    )


    val brush = Brush.horizontalGradient(listOf(Color(0xFF303F9F), Color(0xFF26A69A)))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = brush)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text("Flights from Surabaya to Bali", fontSize = 24.sp, color = Color.White, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ){

                items(flights) { flight ->
                    FlightListItem(flight = flight)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun FlightListItem(flight: Flight) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = flight.departure,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = flight.departureTime,
                        fontSize = 14.sp
                    )
                }
                Text(
                    text = "${flight.duration} hours",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                )
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = flight.destination,
                        textAlign = TextAlign.End,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = flight.arrivalTime,
                        textAlign = TextAlign.End,
                        fontSize = 14.sp
                    )
                }
            }

            Divider(
                color = Color.Gray,
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Flight Number: ${flight.number}",
                    fontSize = 14.sp
                )
                Text(
                    text = "IDR ${flight.price}",
                    textAlign = TextAlign.End,
                    fontSize = 14.sp
                )
            }
        }
    }
}

data class Flight(
    val number: String,
    val departure: String,
    val destination: String,
    val airline: String,
    val departureTime: String,
    val arrivalTime: String,
    val price: Int,
    val duration: String
)