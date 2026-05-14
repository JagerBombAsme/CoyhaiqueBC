package com.example.coyhaiquebc.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Forest
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Museum
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.ui.graphics.vector.ImageVector

data class Category(
    val id: String,
    val nombre: String,
    val descripcion: String,
    val icono: ImageVector,
    val colorFondo: Long,
    val ruta: String
)

val homeCategories = listOf(
    Category(
        id = "gastronomia",
        nombre = "Gastronomía",
        descripcion = "Sabores de la Patagonia",
        icono = Icons.Default.Restaurant,
        colorFondo = 0xFFE8F2FA,
        ruta = "categoria/gastronomia"
    ),
    Category(
        id = "alojamiento",
        nombre = "Alojamiento",
        descripcion = "Dónde quedarse en Coyhaique",
        icono = Icons.Default.Home,
        colorFondo = 0xFFEAF4EC,
        ruta = "categoria/alojamiento"
    ),
    Category(
        id = "cultura",
        nombre = "Cultura y patrimonio",
        descripcion = "Historia e identidad local",
        icono = Icons.Default.Museum,
        colorFondo = 0xFFF3EEF8,
        ruta = "categoria/cultura"
    ),
    Category(
        id = "aventura",
        nombre = "Aventura y deporte",
        descripcion = "Adrenalina en la Patagonia",
        icono = Icons.Default.Terrain,
        colorFondo = 0xFFFFF3E8,
        ruta = "categoria/aventura"
    ),
    Category(
        id = "transporte",
        nombre = "Transporte",
        descripcion = "Cómo moverse por la ciudad",
        icono = Icons.Default.DirectionsBus,
        colorFondo = 0xFFE8F4FF,
        ruta = "categoria/transporte"
    ),
    Category(
        id = "comercio",
        nombre = "Comercio local",
        descripcion = "Tiendas y emprendedores",
        icono = Icons.Default.Storefront,
        colorFondo = 0xFFFFF8E8,
        ruta = "categoria/comercio"
    ),
    Category(
        id = "naturaleza",
        nombre = "Naturaleza y parques",
        descripcion = "Reservas y paisajes únicos",
        icono = Icons.Default.Forest,
        colorFondo = 0xFFEAF6EC,
        ruta = "categoria/naturaleza"
    )
)
