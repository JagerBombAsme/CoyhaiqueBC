package com.coyhaiquebc.data.model

import com.coyhaiquebc.R


val categoriesPrueba = listOf(
    Category(
        id = "comer",
        nombre_es = "Comer algo",
        nombre_en = "Eat something",
        descripcion_es = "Restaurantes, cafés y comida local",
        descripcion_en = "Restaurants, cafés and local food",
        imagen = R.drawable.ic_launcher_background,
        ruta = "explore/gastronomia"
    ),
    Category(
        id = "rutas",
        nombre_es = "Explorar rutas",
        nombre_en = "Explore routes",
        descripcion_es = "Senderos, miradores y naturaleza",
        descripcion_en = "Trails, viewpoints and nature",
        imagen = R.drawable.ic_launcher_background,
        ruta = "explore/rutas"
    )
)
