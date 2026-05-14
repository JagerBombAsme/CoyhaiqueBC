package com.example.coyhaiquebc.navigation

object Routes {
    const val HOME = "home"
    const val FAVORITES = "favorites"
    const val EXPLORE = "explore/{category}"

    fun explore(category: String): String {
        return "explore/$category"
    }
}