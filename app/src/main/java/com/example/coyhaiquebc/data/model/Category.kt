package com.example.coyhaiquebc.data.model

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.Forest
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.filled.Terrain
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.coyhaiquebc.R
import com.example.coyhaiquebc.navigation.Routes


data class Category(
    val id: String,
    val nombre: String,
    val descripcion: String,
    @DrawableRes val imagen: Int,
    val ruta: String
)
val homeCategories = listOf(
    Category(
        id = "gastronomia",
        nombre = "Gastronomía",
        descripcion = "Sabores de la Patagonia",
        imagen = R.drawable.categoria_gastronomia,
        ruta = Routes.category("gastronomia")
    ),
    Category(
        id = "alojamiento",
        nombre = "Alojamiento",
        descripcion = "Dónde quedarse en Coyhaique",
        imagen = R.drawable.categoria_alojamiento,
        ruta = Routes.category("alojamientos")
    ),
    Category(
        id = "cultura",
        nombre = "Cultura",
        descripcion = "encuentro",
        imagen = R.drawable.categoria_cultura,
        ruta = Routes.category("cultura")
    ),
    Category(
        id = "aventura",
        nombre = "Aventura y deporte",
        descripcion = "Adrenalina en la Patagonia",
        imagen = R.drawable.categoria_deporte,
        ruta = Routes.category("aventura")
    ),
    Category(
        id = "transporte",
        nombre = "Transporte",
        descripcion = "Cómo moverse por la ciudad",
        imagen = R.drawable.categoria_transporte,
        ruta = Routes.category("transporte")
    ),
    Category(
        id = "naturaleza",
        nombre = "Naturaleza y parques",
        descripcion = "Reservas y paisajes únicos",
        imagen = R.drawable.categoria_naturaleza,
        ruta = Routes.category("naturaleza")
    )
)