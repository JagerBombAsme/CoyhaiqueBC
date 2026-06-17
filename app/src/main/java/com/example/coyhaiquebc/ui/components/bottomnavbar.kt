package com.example.coyhaiquebc.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.coyhaiquebc.navigation.Routes
import com.example.coyhaiquebc.ui.screens.BubbleNavItem
import kotlinx.coroutines.delay

@Composable
fun BottomNavBar(
    navController: NavController,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.White,
    selectedColor: Color = Color(0xFF7EC8F3),
    unselectedColor: Color = Color(0xFFC8C8C8)
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var showLabel by remember { mutableStateOf(true) }

    // Reiniciar la animación de la etiqueta cuando cambia la ruta
    LaunchedEffect(currentRoute) {
        showLabel = true
        delay(1500)
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
                // Item Inicio
                BubbleNavItem(
                    selected = currentRoute == Routes.HOME,
                    showLabel = showLabel,
                    label = "Inicio",
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Inicio",
                            modifier = Modifier.size(24.dp)
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

                // Item Perfil
                BubbleNavItem(
                    selected = currentRoute == Routes.PROFILE,
                    showLabel = showLabel,
                    label = "Perfil",
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Perfil",
                            modifier = Modifier.size(24.dp)
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