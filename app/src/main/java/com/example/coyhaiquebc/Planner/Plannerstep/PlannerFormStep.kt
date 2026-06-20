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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Brush
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
import com.example.coyhaiquebc.Planner.components.PlannerDestinationButton
import com.example.coyhaiquebc.Planner.components.PlannerFieldCard
import java.text.SimpleDateFormat
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

    val fromText = "Origen"
    val toText = "Destino"
    val titlePart1 = "Planifica tu ruta,"
    val titlePart2 = "Comienza el viaje."

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
            alpha = 0.25f
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.White.copy(alpha = 0.6f), Color.White.copy(alpha = 0.9f))
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 50.dp,
                    bottom = bottomPadding + 24.dp
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = titlePart1,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF1A1A1A),
                    letterSpacing = (-0.5).sp
                )
                Text(
                    text = titlePart2,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF2F7D75),
                    letterSpacing = (-0.5).sp
                )

                Spacer(modifier = Modifier.height(28.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                painter = painterResource(id = android.R.drawable.ic_menu_directions),
                                contentDescription = null,
                                modifier = Modifier.size(22.dp),
                                tint = Color(0xFF2F7D75)
                            )
                            Spacer(modifier = Modifier.width(14.dp))
                            Column {
                                Text(
                                    text = fromText,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Gray,
                                    letterSpacing = 0.5.sp
                                )
                                Text(
                                    text = "Coyhaique, Chile",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color(0xFF1A1A1A)
                                )
                            }
                        }


                        Box(
                            modifier = Modifier
                                .padding(start = 11.dp, top = 8.dp, bottom = 8.dp)
                                .width(1.5.dp)
                                .height(20.dp)
                                .background(Color(0xFF2F7D75).copy(alpha = 0.3f))
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                painter = painterResource(id = android.R.drawable.ic_menu_mylocation),
                                contentDescription = null,
                                modifier = Modifier.size(22.dp),
                                tint = Color(0xFFE07A5F)
                            )
                            Spacer(modifier = Modifier.width(14.dp))
                            Column {
                                Text(
                                    text = toText,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Gray,
                                    letterSpacing = 0.5.sp
                                )
                                PlannerDestinationButton(
                                    destinations = destinations,
                                    selectedDestination = selectedDestination,
                                    onDestinationChange = onDestinationChange
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
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

                Spacer(modifier = Modifier.height(32.dp))


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        text = "Agendado Recientemente",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1A1A)
                    )
                    Text(
                        text = "Ver más",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2F7D75)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Cerro Castillo",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF1A1A1A)
                            )
                            Text(
                                text = "14 Ene | 2 Asientos",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_myplaces),
                            contentDescription = null,
                            tint = Color(0xFF2F7D75).copy(alpha = 0.7f),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
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
                        Text("Aceptar", fontWeight = FontWeight.Bold, color = Color(0xFF2F7D75))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text("Cancelar", color = Color.Gray)
                    }
                }
            ) {
                DatePicker(
                    state = datePickerState,
                    showModeToggle = false,
                    title = {
                        Text(
                            text = selectDateText,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 24.dp, top = 16.dp)
                        )
                    }
                )
            }
        }

        if (showPeoplePicker) {
            androidx.compose.material3.AlertDialog(
                onDismissRequest = { showPeoplePicker = false },
                shape = RoundedCornerShape(24.dp),
                containerColor = Color.White,
                title = {
                    Text(
                        text = selectPeopleText,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A1A1A)
                    )
                },
                text = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 12.dp)
                        ) {
                            IconButton(
                                onClick = { if (tempPeople > 1) tempPeople-- },
                                modifier = Modifier.background(Color(0xFFF0F0F0), RoundedCornerShape(12.dp))
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Remove,
                                    contentDescription = null,
                                    tint = Color(0xFF2F7D75)
                                )
                            }
                            Text(
                                text = "$tempPeople",
                                fontSize = 44.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color(0xFF1A1A1A),
                                modifier = Modifier.padding(horizontal = 28.dp)
                            )
                            IconButton(
                                onClick = { if (tempPeople < 20) tempPeople++ },
                                modifier = Modifier.background(Color(0xFFF0F0F0), RoundedCornerShape(12.dp))
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = null,
                                    tint = Color(0xFF2F7D75)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Máximo 20 personas por reserva",
                            fontSize = 12.sp,
                            color = Color.Gray
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
                        Text("Confirmar", fontWeight = FontWeight.Bold, color = Color(0xFF2F7D75))
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            tempPeople = people.toIntOrNull() ?: 2
                            showPeoplePicker = false
                        }
                    ) {
                        Text("Cancelar", color = Color.Gray)
                    }
                }
            )
        }
    }
}