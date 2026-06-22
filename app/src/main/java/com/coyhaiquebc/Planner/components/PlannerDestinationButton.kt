package com.coyhaiquebc.Planner.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coyhaiquebc.R
import com.coyhaiquebc.data.model.DestinationDto
import com.coyhaiquebc.Planner.PlannerColors

@Composable
fun PlannerDestinationButton(
    destinations: List<DestinationDto>,
    selectedDestination: DestinationDto?,
    onDestinationChange: (DestinationDto) -> Unit
) {
    val configuration = LocalConfiguration.current
    val currentLanguage = configuration.locales[0].language

    var expanded by remember { mutableStateOf(false) }

    val selectedDestinationLabel = stringResource(R.string.planner_destination_selected_label)
    val noDestinationsText = stringResource(R.string.planner_no_destinations_available)
    val selectDestinationText = stringResource(R.string.planner_select_destination)

    val selectedName = selectedDestination?.getNombre(currentLanguage)

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(PlannerColors.Surface)
                .clickable {
                    if (destinations.isNotEmpty()) {
                        expanded = true
                    }
                }
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                Text(
                    text = selectedDestinationLabel,
                    color = PlannerColors.TextSecondary,
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = selectedName
                        ?: if (destinations.isEmpty()) noDestinationsText
                        else selectDestinationText,
                    color = PlannerColors.TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            destinations.forEach { destination ->
                DropdownMenuItem(
                    text = {
                        Column {
                            Text(
                                text = destination.getNombre(currentLanguage),
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    },
                    onClick = {
                        onDestinationChange(destination)
                        expanded = false
                    }
                )
            }
        }
    }
}