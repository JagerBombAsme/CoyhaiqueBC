package com.example.coyhaiquebc.Planner.Plannerstep

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coyhaiquebc.R
import com.example.coyhaiquebc.Planner.PlannerColors
import com.example.coyhaiquebc.Planner.components.PlannerOptionCard
import com.example.coyhaiquebc.Planner.components.PlannerPrimaryButton
import com.example.coyhaiquebc.data.model.TransportRouteDto
import kotlin.collections.forEach

@Composable
fun PlannerAvailabilityStep(
    destination: String,
    routes: List<TransportRouteDto>,
    onOptionSelected: (TransportRouteDto) -> Unit,
    onBackClick: () -> Unit,
    bottomPadding: Dp = 0.dp
) {
    // Textos internacionalizados
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
            alpha = 0.32f
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 22.dp,
                    end = 22.dp,
                    top = 90.dp,
                    bottom = bottomPadding + 20.dp
                )
        ) {
            Text(
                text = availabilityTitle,
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 31.sp,
                color = PlannerColors.TextPrimary
            )

            Spacer(modifier = Modifier.height(24.dp))

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