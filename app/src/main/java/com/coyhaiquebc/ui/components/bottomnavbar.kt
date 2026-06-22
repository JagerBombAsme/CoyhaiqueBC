package com.coyhaiquebc.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.coyhaiquebc.R
import com.coyhaiquebc.navigation.Routes
import com.coyhaiquebc.ui.screens.BubbleNavItem
import kotlinx.coroutines.delay

@Composable
fun BottomNavBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    selectedColor: Color = Color(0xFF243B55),
    unselectedColor: Color = Color(0xFFC8C8C8)
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var showLabel by remember { mutableStateOf(true) }


    LaunchedEffect(currentRoute) {
        showLabel = true
        delay(700)
        showLabel = false
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 34.dp, vertical = 30.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            shape = RoundedCornerShape(30.dp),
            color = containerColor,
            shadowElevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 28.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BubbleNavItem(
                    selected = currentRoute == Routes.HOME,
                    showLabel = showLabel,
                    label = "Inicio",
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Inicio",
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    onClick = {
                        navController.navigate(Routes.HOME) {
                            launchSingleTop = true
                            popUpTo(Routes.HOME) {
                                inclusive = false
                            }
                        }
                    },
                    selectedColor = selectedColor,
                    unselectedColor = unselectedColor
                )


                BubbleNavItem(
                    selected = currentRoute == Routes.PLANNER,
                    showLabel = showLabel,
                    label = "Planificador",
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.van_icon_125763),
                            contentDescription = "Planificador",
                            modifier = Modifier
                                .size(60.dp)
                                .graphicsLayer {
                                    scaleX = -1f
                                }
                        )
                    },
                    onClick = {
                        navController.navigate(Routes.PLANNER) {
                            launchSingleTop = true
                            popUpTo(Routes.PLANNER) {
                                inclusive = false
                            }
                        }
                    },
                    selectedColor = selectedColor,
                    unselectedColor = unselectedColor
                )
                BubbleNavItem(
                    selected = currentRoute == Routes.MAP,
                    showLabel = showLabel,
                    label = "Mapa",
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Map,
                            contentDescription = "Mapa",
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    onClick = {
                        if (currentRoute != Routes.MAP) {
                            navController.navigate(Routes.MAP) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(Routes.HOME) {
                                    saveState = true
                                }
                            }
                        }
                    },
                    selectedColor = selectedColor,
                    unselectedColor = unselectedColor
                )
                BubbleNavItem(
                    selected = currentRoute == Routes.PROFILE,
                    showLabel = showLabel,
                    label = "Perfil",
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Perfil",
                            modifier = Modifier.size(40.dp)
                        )
                    },
                    onClick = {
                        navController.navigate(Routes.PROFILE) {
                            launchSingleTop = true
                        }
                    },
                    selectedColor = selectedColor,
                    unselectedColor = unselectedColor
                )

            }
        }
    }
}