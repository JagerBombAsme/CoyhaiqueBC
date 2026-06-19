package com.example.coyhaiquebc.Planner.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coyhaiquebc.data.model.TransportRouteDto
import com.example.coyhaiquebc.Planner.PlannerColors

@Composable
fun PlannerOptionCard(
    route: TransportRouteDto,
    onClick: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val currentLanguage = configuration.locales[0].language

    // ✅ Obtener textos traducidos
    val origen = route.getOrigen(currentLanguage)
    val destino = route.getDestino(currentLanguage)
    val price = route.getPriceFormatted(currentLanguage)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(82.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(PlannerColors.OptionCard)
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        Text(
            text = origen,
            modifier = Modifier.align(Alignment.TopStart),
            color = PlannerColors.TextPrimary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = destino,
            modifier = Modifier.align(Alignment.TopEnd),
            color = PlannerColors.TextPrimary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )

        Icon(
            imageVector = Icons.Default.DirectionsBus,
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center),
            tint = PlannerColors.TextPrimary
        )

        Text(
            text = route.departureTime ?: "--:--",
            modifier = Modifier.align(Alignment.BottomStart),
            color = PlannerColors.TextPrimary,
            fontSize = 11.sp
        )

        Text(
            text = route.arrivalTime ?: "--:--",
            modifier = Modifier.align(Alignment.BottomEnd),
            color = PlannerColors.TextPrimary,
            fontSize = 11.sp
        )

        Text(
            text = price,
            modifier = Modifier.align(Alignment.BottomCenter),
            color = PlannerColors.TextPrimary,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold
        )
    }
}