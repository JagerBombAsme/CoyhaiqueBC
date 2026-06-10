package com.example.coyhaiquebc.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.coyhaiquebc.ui.screens.HomeScreen
import com.example.coyhaiquebc.ui.screens.CategoryScreen
import com.example.coyhaiquebc.ui.screens.ProfileScreen
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

        composable(Routes.PROFILE) {
            ProfileScreen()
        }

        composable(
            route = Routes.CATEGORY,
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val category = backStackEntry.arguments?.getString("category") ?: ""

            CategoryScreen(
                category = category,
                navController = navController
            )
        }
    }
}