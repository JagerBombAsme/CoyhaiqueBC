package com.coyhaiquebc.Planner.Plannerstep

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coyhaiquebc.R
import com.coyhaiquebc.Planner.PlannerColors
import com.coyhaiquebc.Planner.components.PlannerOptionCard
import com.coyhaiquebc.Planner.components.PlannerPrimaryButton
import com.coyhaiquebc.data.model.TransportRouteDto
import kotlin.collections.forEach

@Composable
fun PlannerAvailabilityStep(
    destination: String,
    routes: List<com.coyhaiquebc.data.model.TransportRouteDto>,
    onOptionSelected: (com.coyhaiquebc.data.model.TransportRouteDto) -> Unit,
    onBackClick: () -> Unit,
    bottomPadding: Dp = 0.dp
) {

    val availabilityTitle = stringResource(R.string.planner_availability_title, destination)
    val noOptionsText = stringResource(R.string.planner_availability_no_options)
    val backButtonText = stringResource(R.string.planner_availability_back)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.fondodisponibilidad),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            alpha = 0.25f
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 22.dp,
                    end = 22.dp,
                    top = 70.dp,
                    bottom = bottomPadding + 20.dp
                )
        ) {
            Text(
                text = availabilityTitle,
                fontSize = 28.sp,
                fontWeight = FontWeight.Black,
                lineHeight = 34.sp,
                color = Color(0xFF1E1E1B)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Selecciona la mejor opción para tu viaje",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(28.dp))

            if (routes.isEmpty()) {
                Text(
                    text = noOptionsText,
                    color = PlannerColors.TextSecondary,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                PlannerPrimaryButton(
                    text = backButtonText,
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
}