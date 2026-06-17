package com.example.coyhaiquebc.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BubbleNavItem(
    selected: Boolean,
    showLabel: Boolean,
    label: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selectedColor: Color = Color(0xFF7EC8F3),
    unselectedColor: Color = Color(0xFFC8C8C8),
    labelColor: Color = Color(0xFF7EC8F3)
) {
    Box(
        modifier = modifier
            .width(80.dp)
            .height(72.dp),
        contentAlignment = Alignment.Center
    ) {
        // Contenedor del ícono
        Box(
            modifier = Modifier
                .size(56.dp)
                .offset(y = if (selected) (-16).dp else 0.dp),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier
                    .size(if (selected) 56.dp else 48.dp),
                shape = CircleShape,
                color = if (selected) selectedColor else Color.Transparent,
                shadowElevation = if (selected) 8.dp else 0.dp,
                onClick = onClick
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CompositionLocalProvider(
                        LocalContentColor provides if (selected) Color.White else unselectedColor
                    ) {
                        icon()
                    }
                }
            }
        }

        // Etiqueta
        AnimatedVisibility(
            visible = selected && showLabel,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = (-4).dp)
        ) {
            Text(
                text = label,
                color = labelColor,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}