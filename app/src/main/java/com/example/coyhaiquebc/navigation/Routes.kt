package com.example.coyhaiquebc.navigation

object Routes {
    const val HOME = "home"
    const val FAVORITES = "favorites"
    const val CULTURA = "cultura"
    const val WELCOME = "bienvenida"
    const val PLANNER = "prereserva"

    const val ROUTED = "aventura/{routeId}"
    const val PROFILE = "perfil"
    const val CATEGORY = "category/{category}"
    const val MAP = "map"
    const val PLACE_DETAIL = "place_detail/{id}"


    fun category(category: String): String {
        return "category/$category"
    }

    fun placeDetail(id: String): String {
        return "place_detail/$id"
    }

    fun routeDetail(routeId: String): String {
        return "detalleruta/$routeId"
    }
}