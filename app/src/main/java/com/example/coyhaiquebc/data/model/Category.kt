package com.example.coyhaiquebc.data.model

import androidx.annotation.DrawableRes
import com.example.coyhaiquebc.R
import com.example.coyhaiquebc.navigation.Routes


data class Category(
    val id: String,
    val nombre_es: String,
    val nombre_en: String,
    val descripcion_es: String,
    val descripcion_en: String,
    @DrawableRes val imagen: Int,
    val ruta: String
) {

    fun getNombre(language: String): String {
        return when (language) {
            "en" -> nombre_en.ifEmpty { nombre_es }
            else -> nombre_es
        }
    }


    fun getDescripcion(language: String): String {
        return when (language) {
            "en" -> descripcion_en.ifEmpty { descripcion_es }
            else -> descripcion_es
        }
    }
}


val homeCategories = listOf(
    Category(
        id = "gastronomia",
        nombre_es = "Gastronomía",
        nombre_en = "Gastronomy",
        descripcion_es = "Sabores de la Patagonia",
        descripcion_en = "Flavors of Patagonia",
        imagen = R.drawable.categoria_gastronomia,
        ruta = Routes.category("gastronomia")
    ),
    Category(
        id = "alojamiento",
        nombre_es = "Alojamiento",
        nombre_en = "Accommodation",
        descripcion_es = "Dónde quedarse en Coyhaique",
        descripcion_en = "Where to stay in Coyhaique",
        imagen = R.drawable.categoria_alojamiento,
        ruta = Routes.category("alojamientos")
    ),
    Category(
        id = "cultura",
        nombre_es = "Cultura",
        nombre_en = "Culture",
        descripcion_es = "Encuentro con la historia",
        descripcion_en = "Encounter with history",
        imagen = R.drawable.categoria_cultura,
        ruta = Routes.category("cultura")
    ),
    Category(
        id = "aventura",
        nombre_es = "Rutas",
        nombre_en = "Routes",
        descripcion_es = "Adrenalina en la Patagonia",
        descripcion_en = "Adrenaline in Patagonia",
        imagen = R.drawable.categoria_deporte,
        ruta = Routes.category("aventura")
    ),
    Category(
        id = "transporte",
        nombre_es = "Transporte",
        nombre_en = "Transport",
        descripcion_es = "Cómo moverse por la ciudad",
        descripcion_en = "How to get around the city",
        imagen = R.drawable.categoria_transporte,
        ruta = Routes.category("transporte")
    ),
    Category(
        id = "naturaleza",
        nombre_es = "Naturaleza y parques",
        nombre_en = "Nature and parks",
        descripcion_es = "Reservas y paisajes únicos",
        descripcion_en = "Reserves and unique landscapes",
        imagen = R.drawable.categoria_naturaleza,
        ruta = Routes.category("naturaleza")
    )
)


fun List<Category>.filterByLanguage(language: String): List<Category> {
    return this.map { category ->
        category.copy(
            nombre_es = category.getNombre(language),
            descripcion_es = category.getDescripcion(language)
        )
    }
}