package com.example.coyhaiquebc.navigation

object Routes {
    const val HOME = "home"
    const val FAVORITES = "favorites"
    const val ALOJAMIENTO = "alojamiento"
    const val WELCOME = "bienvenida"
    const val PLANNER = "prereserva"

    const val ROUTED = "detalleruta"
    const val PROFILE = "perfil"
    const val CATEGORY = "category/{category}"
    const val MAP = "map"


    fun category(category: String): String {
        return "category/$category"
    }
}