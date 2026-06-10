package com.example.coyhaiquebc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coyhaiquebc.navigation.Routes
import androidx.compose.foundation.layout.navigationBarsPadding
@Composable
fun BottomNavBar(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 28.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(68.dp)
                .clip(RoundedCornerShape(34.dp)),
            color = Color(0xFF111111),
            tonalElevation = 8.dp,
            shadowElevation = 10.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate(Routes.HOME) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "Inicio"
                        )
                    },
                    label = {
                        Text("Inicio")
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF111111),
                        selectedTextColor = Color.White,
                        unselectedIconColor = Color.White.copy(alpha = 0.72f),
                        unselectedTextColor = Color.White.copy(alpha = 0.72f),
                        indicatorColor = Color(0xFFE6E6E1)
                    )
                )

                NavigationBarItem(
                    selected = false,
                    onClick = {
                        navController.navigate(Routes.PROFILE) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Perfil"
                        )
                    },
                    label = {
                        Text("Perfil")
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF111111),
                        selectedTextColor = Color.White,
                        unselectedIconColor = Color.White.copy(alpha = 0.72f),
                        unselectedTextColor = Color.White.copy(alpha = 0.72f),
                        indicatorColor = Color(0xFFE6E6E1)
                    )
                )
            }
        }
    }
}