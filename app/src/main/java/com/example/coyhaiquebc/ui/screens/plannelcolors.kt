package com.example.coyhaiquebc.ui.screens

import androidx.compose.ui.graphics.Color

object PlannerColors {
    val StrongCyan = Color(0xFF0CBDCC)
    val TwilightIndigo = Color(0xFF2A3D5E)
    val SmartBlue = Color(0xFF2567C5)
    val Silver = Color(0xFFB3AFAB)
    val DodgerBlue = Color(0xFF4898FB)

    val Primary = StrongCyan
    val PrimaryDark = TwilightIndigo
    val PrimaryLight = DodgerBlue

    val Secondary = SmartBlue
    val SecondaryLight = StrongCyan.copy(alpha = 0.15f)

    val Background = Color(0xFFF6F7F5)
    val Surface = Color.White
    val SurfaceDark = TwilightIndigo

    val TextPrimary = TwilightIndigo
    val TextSecondary = Silver
    val TextOnPrimary = Color.White
    val TextOnSecondary = Color.White

    val Error = Color(0xFFE53935)
    val Success = StrongCyan

    val SegmentBackground = StrongCyan.copy(alpha = 0.15f)
    val SegmentSelected = StrongCyan
    val SegmentUnselected = Color.Transparent

    val CardSelected = SmartBlue
    val CardUnselected = Color.White
    val CardTextSelected = Color.White
    val CardTextUnselected = TwilightIndigo

    val FieldCard = DodgerBlue
    val FieldCardText = Color.White

    val OptionCard = StrongCyan.copy(alpha = 0.2f)
    val OptionCardText = TwilightIndigo

    val QRBackground = Color.White

    val ButtonPrimary = StrongCyan
    val ButtonText = Color.White

    val Border = Silver.copy(alpha = 0.3f)
}