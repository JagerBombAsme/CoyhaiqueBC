package com.coyhaiquebc.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val AppColors = lightColorScheme(
    primary = IceBlue,
    onPrimary = SnowWhite,

    secondary = IceLight,
    onSecondary = SnowWhite,

    background = SlateLight,
    onBackground = CharcoalPrimary,

    surface = SlateCard,
    onSurface = CharcoalPrimary,

    surfaceVariant = IceSurface,
    onSurfaceVariant = CharcoalMuted,

    outline = SlateBorder,

    error = AlertRed
)

@Composable
fun CoyhaiqueBCTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppColors,
        content = content
    )
}