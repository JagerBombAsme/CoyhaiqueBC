package com.example.coyhaiquebc.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.coyhaiquebc.ui.screens.CategoryScreen
import com.example.coyhaiquebc.ui.screens.HomeScreen
import com.example.coyhaiquebc.ui.screens.MapScreen
import com.example.coyhaiquebc.ui.screens.PlanificacionScreen
import com.example.coyhaiquebc.ui.screens.ProfileScreen
import com.example.coyhaiquebc.ui.screens.WelcomeScreen
import com.example.coyhaiquebc.ui.screens.RouteDetailScreen
import com.example.coyhaiquebc.ui.screens.CulturaScreen
import com.example.coyhaiquebc.ui.screens.PlaceDetailScreen
import com.example.coyhaiquebc.ui.screens.AventuraScreen
import com.example.coyhaiquebc.ui.screens.MapScreen

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
            ProfileScreen(navController = navController)
        }

        composable(Routes.PLANNER) {
            PlanificacionScreen(navController = navController)
        }
        composable(Routes.WELCOME) {
            WelcomeScreen(navController = navController)
        }
        composable(Routes.CULTURA) {
            CulturaScreen(navController = navController)
        }
        composable(
            route = Routes.ROUTED,
            arguments = listOf(navArgument("routeId") { type = NavType.StringType })
        ) { backStackEntry ->
            val routeId = backStackEntry.arguments?.getString("routeId") ?: ""
            RouteDetailScreen(
                navController = navController,
                routeId = routeId
            )
        }
        composable(
            route = Routes.MAP
        ) {
            MapScreen(navController = navController)
        }
        
        composable(
            route = Routes.PLACE_DETAIL,
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: ""
            PlaceDetailScreen(
                navController = navController,
                placeId = id
            )
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

