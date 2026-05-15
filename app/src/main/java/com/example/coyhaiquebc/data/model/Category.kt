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
        ruta = "categoria/gastronomia"
    ),
    Category(
        id = "alojamiento",
        nombre = "Alojamiento",
        descripcion = "Dónde quedarse en Coyhaique",
        imagen = R.drawable.categoria_alojamiento,
        ruta = "categoria/alojamiento"
    ),
    Category(
        id = "cultura",
        nombre = "Cultura",
        descripcion = "encuentro",
        imagen = R.drawable.categoria_cultura,
        ruta = "categoria/cultura"
    ),
    Category(
        id = "aventura",
        nombre = "Aventura y deporte",
        descripcion = "Adrenalina en la Patagonia",

        imagen = R.drawable.categoria_cultura,
        ruta = "categoria/aventura"
    ),
    Category(
        id = "transporte",
        nombre = "Transporte",
        descripcion = "Cómo moverse por la ciudad",
        imagen = R.drawable.categoria_transporte,
        ruta = "categoria/transporte"
    ),
    Category(
        id = "comercio",
        nombre = "Comercio local",
        descripcion = "Tiendas y emprendedores",
        imagen = R.drawable.categoria_cultura,
        ruta = "categoria/comercio"
    ),
    Category(
        id = "naturaleza",
        nombre = "Naturaleza y parques",
        descripcion = "Reservas y paisajes únicos",
        imagen = R.drawable.ic_launcher_background,
        ruta = "categoria/naturaleza"
    )
)
