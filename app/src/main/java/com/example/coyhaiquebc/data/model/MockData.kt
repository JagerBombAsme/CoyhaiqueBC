package com.example.coyhaiquebc.data.model

// Archivo de datos de prueba para la aplicación

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*


val categoriesPrueba = listOf(
    Category(
        id = "comer",
        nombre = "Comer algo",
        descripcion = "Restaurantes, cafés y comida local",
        icono = Icons.Default.Restaurant,
        colorFondo = 0xFFB88B55,
        ruta = "explore/gastronomia"
    ),
    Category(
        id = "rutas",
        nombre = "Explorar rutas",
        descripcion = "Senderos, miradores y naturaleza",
        icono = Icons.Default.Terrain,
        colorFondo = 0xFF9F7445,
        ruta = "explore/rutas"
    )
)
