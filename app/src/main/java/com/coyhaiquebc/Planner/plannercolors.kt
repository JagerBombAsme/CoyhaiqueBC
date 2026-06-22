package com.coyhaiquebc.Planner

import androidx.compose.ui.graphics.Color

object PlannerColors {
    val PremiumBlue = Color(0xFF243B55)
    val MidnightNavy = Color(0xFF1A2A3A)
    val SlateGray = Color(0xFF7F8C8D)
    val OceanBlue = Color(0xFF0984E3)
    val EmeraldPremium = Color(0xFF00B894)

    val Primary = PremiumBlue
    val PrimaryDark = MidnightNavy
    val PrimaryLight = OceanBlue

    val Secondary = OceanBlue
    val SecondaryLight = PremiumBlue.copy(alpha = 0.15f)

    val Background = Color(0xFFF8F9FA)
    val Surface = Color.White
    val SurfaceDark = MidnightNavy

    val TextPrimary = MidnightNavy
    val TextSecondary = SlateGray
    val TextOnPrimary = Color.White
    val TextOnSecondary = Color.White

    val Error = Color(0xFFD63031)
    val Success = EmeraldPremium

    val SegmentBackground = PremiumBlue.copy(alpha = 0.1f)
    val SegmentSelected = PremiumBlue
    val SegmentUnselected = Color.Transparent

    val CardSelected = PremiumBlue
    val CardUnselected = Color.White
    val CardTextSelected = Color.White
    val CardTextUnselected = MidnightNavy

    val FieldCard = PremiumBlue
    val FieldCardText = Color.White

    val OptionCard = Color(0xFFF1F2F6)
    val OptionCardText = MidnightNavy

    val QRBackground = Color.White

    val ButtonPrimary = PremiumBlue
    val ButtonText = Color.White

    val Border = Color(0xFFDFE6E9)
}