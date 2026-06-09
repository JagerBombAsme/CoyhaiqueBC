package com.example.coyhaiquebc.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coyhaiquebc.data.repository.PlacesRepository

@Composable
fun TransporteScreen(
    navController: NavController
) {
    val repository = remember { PlacesRepository() }

    var reloadKey by remember { mutableIntStateOf(0) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var transporte by remember { mutableStateOf<List<CategoryItem>>(emptyList()) }

    LaunchedEffect(reloadKey) {
        isLoading = true
        errorMessage = null

        try {
            val places = repository.getPlacesByCategory("transportes")

            transporte = places.map { place ->
                CategoryItem(
                    title = place.title,
                    subtitle = place.subtitle ?: "Coyhaique",
                    description = place.description ?: "Sin descripción disponible.",
                    imageUrl = place.imageUrl
                )
            }
        } catch (e: Exception) {
            errorMessage = "No se pudieron cargar los datos de Transportes."
        } finally {
            isLoading = false
        }
    }

    when {
        isLoading -> {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(text = "Cargando Transportes...")
            }
        }

        errorMessage != null -> {
            Column(modifier = Modifier.padding(24.dp)) {
                Text(text = errorMessage ?: "Error desconocido")

                Spacer(modifier = Modifier.height(12.dp))

                Button(onClick = { reloadKey++ }) {
                    Text("Reintentar")
                }
            }
        }

        else -> {
            Column {
                Button(
                    modifier = Modifier.padding(16.dp),
                    onClick = { reloadKey++ }
                ) {
                    Text("Recargar")
                }

                CategoryListScreen(
                    title = "Transportes de \nCoyhaique",
                    subtitle = "Viajes, rutas y transporte desde y hacia el aeropuerto",
                    searchPlaceholder = "Buscar transporte",
                    tabs = listOf("Todos", "Viajes", "Transfers", "Rutas"),
                    selectedTab = "Todos",
                    featuredItems = transporte,
                    popularItems = transporte,
                    onItemClick = {
                        // Luego navegamos al detalle
                    }
                )
            }
        }
    }
}