package com.example.coyhaiquebc.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun CategoryScreen(
    category: String,
    navController: NavController
) {
    when (category) {
        "alojamientos" -> AlojamientosScreen()
        else -> ComingSoonScreen(category = category)
    }
}
@Composable
fun ComingSoonScreen(category: String) {
    androidx.compose.material3.Text(
        text = "Próximamente: $category"
    )
}