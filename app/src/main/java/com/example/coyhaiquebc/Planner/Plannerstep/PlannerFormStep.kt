package com.example.coyhaiquebc.Planner.Plannerstep

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coyhaiquebc.R
import com.example.coyhaiquebc.data.model.DestinationDto
import com.example.coyhaiquebc.Planner.PlannerColors
import com.example.coyhaiquebc.Planner.components.PlannerPrimaryButton
import com.example.coyhaiquebc.Planner.components.PlannerSegment
import com.example.coyhaiquebc.Planner.components.PlannerDestinationButton
import com.example.coyhaiquebc.Planner.components.PlannerFieldCard

@Composable
fun PlannerFormStep(
    destinations: List<DestinationDto>,
    selectedDestination: DestinationDto?,
    onDestinationChange: (DestinationDto) -> Unit,
    transportType: String,
    onTransportChange: (String) -> Unit,
    date: String,
    onDateChange: (String) -> Unit,
    people: String,
    onPeopleChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    bottomPadding: Dp = 0.dp
) {
    val configureTripText = stringResource(R.string.planner_configure_trip)
    val touristText = stringResource(R.string.planner_tourist)
    val adventureText = stringResource(R.string.planner_adventure)
    val selectDestinationText = stringResource(R.string.planner_select_your_destination)
    val dateSubtitleText = stringResource(R.string.planner_date)
    val peopleSubtitleText = stringResource(R.string.planner_people)
    val searchAvailabilityText = stringResource(R.string.planner_search_availability)

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondocuestionario),
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
                    top = 80.dp,
                    bottom = bottomPadding + 20.dp
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = configureTripText,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 34.sp,
                    color = PlannerColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .clip(RoundedCornerShape(26.dp))
                        .background(PlannerColors.SegmentBackground)
                ) {
                    PlannerSegment(
                        text = touristText,
                        selected = transportType == "Transporte turístico",
                        modifier = Modifier.weight(1f),
                        onClick = { onTransportChange("Transporte turístico") }
                    )

                    PlannerSegment(
                        text = adventureText,
                        selected = transportType == "Transporte aventura",
                        modifier = Modifier.weight(1f),
                        onClick = { onTransportChange("Transporte aventura") }
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = selectDestinationText,
                    fontSize = 20.sp,
                    color = PlannerColors.TextPrimary
                )

                Spacer(modifier = Modifier.height(10.dp))

                PlannerDestinationButton(
                    destinations = destinations,
                    selectedDestination = selectedDestination,
                    onDestinationChange = onDestinationChange
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    PlannerFieldCard(
                        title = date, // Datos dinámicos - NO traducir
                        subtitle = dateSubtitleText,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onDateChange(
                                if (date == "12/01/2027") "15/01/2027" else "12/01/2027"
                            )
                        }
                    )

                    PlannerFieldCard(
                        title = people, // Datos dinámicos - NO traducir
                        subtitle = peopleSubtitleText,
                        modifier = Modifier.weight(1f),
                        onClick = {
                            onPeopleChange(
                                if (people == "2") "3" else "2"
                            )
                        }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }

            PlannerPrimaryButton(
                text = searchAvailabilityText,
                onClick = onSearchClick
            )
        }
    }
}