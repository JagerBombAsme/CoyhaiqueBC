package com.example.coyhaiquebc.Planner.Plannerstep

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
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
    val searchAvailabilityText = stringResource(R.string.planner_search_availability)
    val dateSubtitleText = stringResource(R.string.planner_date)
    val peopleSubtitleText = stringResource(R.string.planner_people)
    val selectDateText = stringResource(R.string.planner_select_date)
    val selectPeopleText = stringResource(R.string.planner_select_people)
    val fromText = "From"
    val toText = "To"

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = System.currentTimeMillis())

    var showPeoplePicker by remember { mutableStateOf(false) }
    var tempPeople by remember { mutableStateOf(people.toIntOrNull() ?: 2) }

    val dateFormat = remember { SimpleDateFormat("dd MMM, yyyy", Locale.getDefault()) }

    fun updateDate(millis: Long?) {
        millis?.let {
            val date = Date(it)
            val formattedDate = dateFormat.format(date)
            onDateChange(formattedDate)
        }
        showDatePicker = false
    }

    Box(modifier = Modifier.fillMaxSize()) {
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
                    top = 60.dp,
                    bottom = bottomPadding + 20.dp
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Find a route,",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Let's make a journey.",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(24.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(16.dp))
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_directions),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = Color(0xFF2F7D75)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = fromText,
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                            Text(
                                text = "Coyhaique, Chile",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                color = Color.Black
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_mylocation),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = Color(0xFF2F7D75)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = toText,
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                            PlannerDestinationButton(
                                destinations = destinations,
                                selectedDestination = selectedDestination,
                                onDestinationChange = onDestinationChange
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    PlannerFieldCard(
                        title = date,
                        subtitle = dateSubtitleText,
                        modifier = Modifier.weight(1f),
                        onClick = { showDatePicker = true }
                    )
                    PlannerFieldCard(
                        title = people,
                        subtitle = peopleSubtitleText,
                        modifier = Modifier.weight(1f),
                        onClick = { showPeoplePicker = true }
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Recent Booked",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                    Text(
                        text = "See more",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF2F7D75)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color.White)
                        .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp))
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Cerro Castillo",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Black
                        )
                        Text(
                            text = "14 Jan 2023 | 2 Seats",
                            fontSize = 12.sp,
                            color = Color.Gray
                        )
                    }
                    Icon(
                        painter = painterResource(id = android.R.drawable.ic_menu_myplaces),
                        contentDescription = null,
                        tint = Color(0xFF2F7D75)
                    )
                }
            }

            PlannerPrimaryButton(
                text = searchAvailabilityText,
                onClick = onSearchClick
            )
        }

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = { updateDate(datePickerState.selectedDateMillis) }) {
                        Text("Accept")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(
                    state = datePickerState,
                    showModeToggle = true,
                    title = {
                        Text(
                            text = selectDateText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                )
            }
        }

        if (showPeoplePicker) {
            androidx.compose.material3.AlertDialog(
                onDismissRequest = { showPeoplePicker = false },
                title = {
                    Text(
                        text = selectPeopleText,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { if (tempPeople > 1) tempPeople-- }) {
                                Icon(
                                    imageVector = Icons.Default.Remove,
                                    contentDescription = null
                                )
                            }
                            Text(
                                text = "$tempPeople",
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 24.dp)
                            )
                            IconButton(onClick = { if (tempPeople < 20) tempPeople++ }) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Maximum 20 people",
                            fontSize = 12.sp,
                            color = PlannerColors.TextSecondary
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onPeopleChange(tempPeople.toString())
                            showPeoplePicker = false
                        }
                    ) {
                        Text("Accept")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            tempPeople = people.toIntOrNull() ?: 2
                            showPeoplePicker = false
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}