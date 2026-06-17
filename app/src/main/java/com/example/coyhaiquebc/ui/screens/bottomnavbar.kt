package com.example.coyhaiquebc.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coyhaiquebc.navigation.Routes
import kotlinx.coroutines.delay

@Composable
fun BottomNavBar(
    navController: NavController
) {
    var selectedRoute by remember { mutableStateOf(Routes.HOME) }
    var showLabel by remember { mutableStateOf(true) }

    LaunchedEffect(selectedRoute) {
        showLabel = true
        delay(1000)
        showLabel = false
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 32.dp, vertical = 14.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            shape = RoundedCornerShape(24.dp),
            color = Color.White,
            shadowElevation = 10.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 28.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BubbleNavItem(
                    selected = selectedRoute == Routes.HOME,
                    showLabel = showLabel && selectedRoute == Routes.HOME,
                    label = "Home",
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home"
                        )
                    },
                    onClick = {
                        selectedRoute = Routes.HOME
                        navController.navigate(Routes.HOME) {
                            launchSingleTop = true
                        }
                    }
                )

                BubbleNavItem(
                    selected = selectedRoute == Routes.PROFILE,
                    showLabel = showLabel && selectedRoute == Routes.PROFILE,
                    label = "Perfil",
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Perfil"
                        )
                    },
                    onClick = {
                        selectedRoute = Routes.PROFILE
                        navController.navigate(Routes.PROFILE) {
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun BubbleNavItem(
    selected: Boolean,
    showLabel: Boolean,
    label: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(110.dp)
            .height(74.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Surface(
            modifier = Modifier
                .size(if (selected) 66.dp else 48.dp)
                .offset(y = if (selected) (-16).dp else 14.dp),
            shape = CircleShape,
            color = if (selected) Color(0xFF7EC8F3) else Color.Transparent,
            shadowElevation = if (selected) 6.dp else 0.dp,
            onClick = onClick
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CompositionLocalProvider(
                    LocalContentColor provides if (selected) Color.White else Color(0xFFCFCFCF)
                ) {
                    icon()
                }
            }
        }

        AnimatedVisibility(
            visible = selected && showLabel,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text(
                text = label,
                color = Color(0xFF7EC8F3)
            )
        }
    }
}