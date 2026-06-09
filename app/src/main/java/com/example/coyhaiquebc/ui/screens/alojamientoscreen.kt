package com.example.coyhaiquebc.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coyhaiquebc.R
import com.example.coyhaiquebc.data.repository.PlacesRepository

@Composable
fun AlojamientosScreen(
    navController: NavController
) {
    val repository = remember { PlacesRepository() }

    var reloadKey by remember { mutableIntStateOf(0) }
    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var alojamientos by remember { mutableStateOf<List<CategoryItem>>(emptyList()) }

    LaunchedEffect(reloadKey) {
        isLoading = true
        errorMessage = null

        try {
            val places = repository.getPlacesByCategory("alojamientos")

            alojamientos = places.map { place ->
                CategoryItem(
                    title = place.title,
                    subtitle = place.subtitle ?: "Coyhaique",
                    description = place.description ?: "Sin descripción disponible.",
                    image = R.drawable.categoria_alojamiento
                )
            }
        } catch (e: Exception) {
            errorMessage = "No se pudieron cargar los alojamientos."
        } finally {
            isLoading = false
        }
    }

    when {
        isLoading -> {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(text = "Cargando alojamientos...")
            }
        }

        errorMessage != null -> {
            Column(
                modifier = Modifier.padding(24.dp)
            ) {
                Text(text = errorMessage ?: "Error desconocido")

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        reloadKey++
                    }
                ) {
                    Text("Reintentar")
                }
            }
        }

        else -> {
            Column {
                Button(
                    modifier = Modifier.padding(16.dp),
                    onClick = {
                        reloadKey++
                    }
                ) {
                    Text("Recargar")
                }

                CategoryListScreen(
                    title = "Encuentra tu\nalojamiento",
                    subtitle = "Hoteles, hostales y cabañas disponibles en Coyhaique.",
                    searchPlaceholder = "Buscar alojamiento",
                    tabs = listOf("Todos", "Hotel", "Hostal", "Cabañas"),
                    selectedTab = "Todos",
                    featuredItems = alojamientos,
                    popularItems = alojamientos,
                    onItemClick = { alojamiento ->
                        // Luego detalle
                    }
                )
            }
        }
    }
}