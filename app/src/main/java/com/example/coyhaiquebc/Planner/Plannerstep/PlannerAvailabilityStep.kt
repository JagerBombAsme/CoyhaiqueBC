package com.example.coyhaiquebc.Planner.Plannerstep


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coyhaiquebc.data.model.TransportRouteDto
import com.example.coyhaiquebc.Planner.PlannerColors
import com.example.coyhaiquebc.Planner.components.PlannerPrimaryButton
import com.example.coyhaiquebc.Planner.components.PlannerOptionCard
@Composable
fun PlannerAvailabilityStep(
    destination: String,
    routes: List<TransportRouteDto>,
    onOptionSelected: (TransportRouteDto) -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Disponibilidad\npara $destination",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 31.sp,
            color = PlannerColors.TextPrimary
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (routes.isEmpty()) {
            Text(
                text = "No hay opciones disponibles para este destino.",
                color = PlannerColors.TextSecondary,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            PlannerPrimaryButton(
                text = "Volver",
                onClick = onBackClick
            )
        } else {
            routes.forEach { route ->
                PlannerOptionCard(
                    route = route,
                    onClick = {
                        onOptionSelected(route)
                    }
                )

                Spacer(modifier = Modifier.height(14.dp))
            }
        }
    }
}