package com.example.coyhaiquebc.data.model

//Pagina de pruebas para el funcionamiento de la app previo a la aplicación de Supabase, que es nuestra base de datos a ocupar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.Color

val homeCategories = listOf(
    CategoryItem(
        id = "comer",
        title = "Comer algo",
        subtitle = "Restaurantes, cafés y comida local",
        icon = Icons.Default.Restaurant,
        color = Color(0xFFB88B55),
        route = "explore/gastronomia"
    ),
    CategoryItem(
        id = "rutas",
        title = "Explorar rutas",
        subtitle = "Senderos, miradores y naturaleza",
        icon = Icons.Default.Terrain,
        color = Color(0xFF9F7445),
        route = "explore/rutas"
    ),
    CategoryItem(
        id = "alojar",
        title = "Buscar alojamiento",
        subtitle = "Hospedajes, cabañas y hoteles",
        icon = Icons.Default.Apartment,
        color = Color(0xFFC8A46D),
        route = "explore/alojamiento"
    ),
    CategoryItem(
        id = "transporte",
        title = "Moverme por la ciudad",
        subtitle = "Buses, transfer y puntos de conexión",
        icon = Icons.Default.DirectionsBus,
        color = Color(0xFFD6B981),
        route = "explore/transporte"
    ),
    CategoryItem(
        id = "panoramas",
        title = "Ver panoramas",
        subtitle = "Actividades, cultura y experiencias",
        icon = Icons.Default.PhotoCamera,
        color = Color(0xFFA8794F),
        route = "explore/panoramas"
    ),
    CategoryItem(
        id = "favoritos",
        title = "Mis favoritos",
        subtitle = "Lugares guardados para visitar después",
        icon = Icons.Default.Favorite,
        color = Color(0xFFC08A5A),
        route = "favorites"
    )
)