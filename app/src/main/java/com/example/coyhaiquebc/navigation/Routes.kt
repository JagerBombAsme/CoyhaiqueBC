package com.example.coyhaiquebc.navigation

object Routes {
    const val HOME = "home"
    const val FAVORITES = "favorites"
    const val ALOJAMIENTO = "alojamiento"

    const val CATEGORY = "category/{category}"

    fun category(category: String): String {
        return "category/$category"
    }
}