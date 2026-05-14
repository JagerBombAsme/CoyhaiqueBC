package com.example.coyhaiquebc.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.coyhaiquebc.ui.screens.HomeScreen



@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(Routes.HOME) {
            HomeScreen(navController = navController)
        }

        composable(
            route = Routes.EXPLORE,
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val category = backStackEntry.arguments?.getString("category") ?: ""

            Text(text = "Explorar categoría: $category")
        }

        composable(Routes.FAVORITES) {
            Text(text = "Favoritos")
        }
    }
}