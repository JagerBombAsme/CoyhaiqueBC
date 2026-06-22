package com.coyhaiquebc.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.coyhaiquebc.ui.screens.CategoryScreen
import com.coyhaiquebc.ui.screens.HomeScreen
import com.coyhaiquebc.ui.screens.MapScreen
import com.coyhaiquebc.ui.screens.PlanificacionScreen
import com.coyhaiquebc.ui.screens.ProfileScreen
import com.coyhaiquebc.ui.screens.WelcomeScreen
import com.coyhaiquebc.ui.screens.RouteDetailScreen
import com.coyhaiquebc.ui.screens.CulturaScreen
import com.coyhaiquebc.ui.screens.PlaceDetailScreen
import com.coyhaiquebc.ui.screens.AventuraScreen

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

