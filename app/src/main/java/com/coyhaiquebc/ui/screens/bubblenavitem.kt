package com.coyhaiquebc.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun BubbleNavItem(
    selected: Boolean,
    showLabel: Boolean,
    label: String,
    icon: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selectedColor: Color = Color(0xFF1D7312),
    unselectedColor: Color = Color(0xFF9E9E9E),
    labelColor: Color = Color(0xFF1D7312)
) {

    var animationPhase by remember { mutableStateOf(0) }

    LaunchedEffect(selected) {
        if (selected) {

            animationPhase = 1
            delay(500)

            animationPhase = 2

            animationPhase = 3
            delay(500)

            animationPhase = 4
            delay(400)


            animationPhase = 0
        } else {
            animationPhase = 0
        }
    }

    val targetOffset = when {
        !selected -> 0.dp
        animationPhase == 1 -> (-20).dp
        animationPhase == 2 -> 2.dp
        animationPhase == 3 -> -20.dp
        animationPhase == 4 -> 0.dp
        else -> 0.dp
    }


    val offsetY by animateDpAsState(
        targetValue = targetOffset,
        animationSpec = when (animationPhase) {
            1 -> tween(
                durationMillis = 400,
                easing = FastOutLinearInEasing
            )
            2 -> spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium
            )
            3 -> tween(durationMillis = 0)
            4 -> spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessMedium
            )
            else -> tween(durationMillis = 0)
        },
        label = "offsetAnimation"
    )

    val targetScale = when {
        !selected -> 1.2f
        animationPhase == 1 -> 0.8f
        animationPhase == 2 -> 1.1f
        animationPhase == 3 -> 0.8f
        animationPhase == 4 -> 1.2f
        else -> 1.2f
    }

    val scale by animateFloatAsState(
        targetValue = targetScale,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "scaleAnimation"
    )

    Box(
        modifier = modifier
            .width(80.dp)
            .height(90.dp),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .size(50.dp)
                .offset(y = offsetY)
                .scale(scale),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier
                    .size(if (selected) 42.dp else 33.dp),
                shape = CircleShape,
                color = if (selected) selectedColor else Color.Transparent,
                shadowElevation = if (selected) 13.dp else 0.dp,
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

        AnimatedVisibility(
            visible = selected && showLabel,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .offset(y = 5.dp)
        ) {
            Text(
                text = label,
                color = labelColor,
                modifier = Modifier.padding(bottom = 5.dp)
            )
        }
    }
}