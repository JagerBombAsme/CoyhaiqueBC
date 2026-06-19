package com.example.coyhaiquebc.data.model


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Terrain
import com.example.coyhaiquebc.R
val categoriesPrueba = listOf(
    Category(
        id = "comer",
        nombre = "Comer algo",
        descripcion = "Restaurantes, cafés y comida local",

        imagen = R.drawable.ic_launcher_background,
        ruta = "explore/gastronomia"
    ),
    Category(
        id = "rutas",
        nombre = "Explorar rutas",
        descripcion = "Senderos, miradores y naturaleza",

        imagen = R.drawable.ic_launcher_background,
        ruta = "explore/rutas"
    )
)
