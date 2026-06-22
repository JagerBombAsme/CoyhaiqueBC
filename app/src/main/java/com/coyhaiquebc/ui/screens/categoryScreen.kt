package com.coyhaiquebc.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CategoryScreen(
    category: String,
    navController: NavController
) {
    when (category) {
        "cultura" -> CulturaScreen(navController = navController)
        "alojamientos" -> AlojamientosScreen(navController = navController)
        "transporte" -> TransporteScreen(navController = navController)
        "gastronomia" -> GastronomiaScreen(navController = navController)
        "aventura" -> AventuraScreen(navController = navController)
        else -> ComingSoonScreen(category = category)
    }
}

@Composable
fun ComingSoonScreen(category: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {  },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE8F2FA),
                contentColor = Color(0xFF2E7BB4)
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.height(56.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
        ) {
            Text(
                text = "Próximamente: ${category.replaceFirstChar { it.uppercase() }}",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}