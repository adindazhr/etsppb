package com.example.mylogin

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    // Navigate to the login screen after a delay
    LaunchedEffect(Unit) {
        delay(SPLASH_SCREEN_DURATION)
        navController.navigate("login")
    }

    val brush = Brush.horizontalGradient(listOf(Color(0xFF303F9F), Color(0xFF26A69A)))

    // You can include any UI elements you want in your splash screen
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = brush),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(Color.White, shape = CircleShape)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ){Image(
            painter = painterResource(id = R.drawable.gi),
            contentDescription = "Garuda Logo",
            modifier = Modifier.size(200.dp)
        )}

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Welcome to",
            color = Color.White,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "FlyGaruda!",
            color = Color.White,
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

private const val SPLASH_SCREEN_DURATION = 2000L
