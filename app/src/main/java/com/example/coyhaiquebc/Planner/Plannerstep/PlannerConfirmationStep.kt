package com.example.coyhaiquebc.Planner.Plannerstep

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coyhaiquebc.data.model.TransportRouteDto
import com.example.coyhaiquebc.Planner.PlannerColors
import com.example.coyhaiquebc.Planner.components.PlannerPrimaryButton


@Composable
fun PlannerConfirmationStep(
    destination: String,
    route: TransportRouteDto?,
    date: String,
    people: String,
    onFinishClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "LISTO",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = PlannerColors.TextPrimary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Presenta este código al subirte al transporte.",
            fontSize = 14.sp,
            lineHeight = 20.sp,
            color = PlannerColors.TextSecondary
        )

        Spacer(modifier = Modifier.height(32.dp))

        Box(
            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(PlannerColors.QRBackground),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.QrCode,
                contentDescription = "QR",
                modifier = Modifier.size(135.dp),
                tint = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            text = """
Destino: $destination
Origen: ${route?.origin ?: "Coyhaique"}
Salida: ${route?.departureTime ?: "--:--"}
Llegada: ${route?.arrivalTime ?: "--:--"}
Precio: ${route?.priceClp?.let { "$$it CLP" } ?: "Por confirmar"}
Fecha: $date
Personas: $people
            """.trimIndent(),
            fontSize = 14.sp,
            lineHeight = 22.sp,
            color = PlannerColors.TextSecondary
        )

        Spacer(modifier = Modifier.height(30.dp))

        PlannerPrimaryButton(
            text = "Finalizar",
            onClick = onFinishClick
        )
    }
}