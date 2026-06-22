package com.coyhaiquebc.ui.screens
import com.coyhaiquebc.Planner.PlannerColors
import com.coyhaiquebc.Planner.components.PlannerPrimaryButton
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.coyhaiquebc.R
import com.coyhaiquebc.data.model.DestinationDto
import com.coyhaiquebc.data.model.TransportRouteDto
import com.coyhaiquebc.data.repository.PlannerRepository
import com.coyhaiquebc.ui.components.BottomNavBar
import kotlinx.coroutines.launch
import com.coyhaiquebc.Planner.Plannerstep.PlannerAvailabilityStep
import com.coyhaiquebc.Planner.Plannerstep.PlannerConfirmationStep
import com.coyhaiquebc.Planner.Plannerstep.PlannerFormStep
import com.coyhaiquebc.Planner.Plannerstep.PlannerWelcomeStep

@Composable
fun PlanificacionScreen(
    navController: NavController
) {
    val configuration = LocalConfiguration.current
    val currentLanguage = configuration.locales[0].language

    val repository = remember { PlannerRepository() }
    val coroutineScope = rememberCoroutineScope()

    var step by remember { mutableIntStateOf(1) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    var destinations by remember { mutableStateOf<List<DestinationDto>>(emptyList()) }
    var routes by remember { mutableStateOf<List<TransportRouteDto>>(emptyList()) }

    var selectedDestination by remember { mutableStateOf<DestinationDto?>(null) }
    var selectedRoute by remember { mutableStateOf<TransportRouteDto?>(null) }

    var transportType by remember { mutableStateOf("Transporte turístico") }
    var date by remember { mutableStateOf("12/01/2027") }
    var people by remember { mutableStateOf("2") }

    val errorLoadingDestinations = stringResource(R.string.planner_error_loading_destinations)
    val errorSelectDestination = stringResource(R.string.planner_select_destination_error)
    val errorLoadingAvailability = stringResource(R.string.planner_error_loading_availability)
    val unknownError = stringResource(R.string.planner_unknown_error)
    val retryText = stringResource(R.string.planner_retry)
    val defaultDestination = stringResource(R.string.planner_default_destination)

    fun loadDestinations() {
        coroutineScope.launch {
            isLoading = true
            errorMessage = null

            try {
                println(" Cargando destinos desde Supabase...")
                destinations = repository.getDestinations()
                println(" Destinos obtenidos: ${destinations.size}")

                if (destinations.isNotEmpty()) {
                    selectedDestination = destinations.firstOrNull()
                    println(" Destino seleccionado: ${selectedDestination?.getNombre(currentLanguage)}")
                } else {
                    println(" No hay destinos disponibles en la base de datos")
                }
            } catch (e: Exception) {
                println(" Error: ${e.message}")
                e.printStackTrace()
                errorMessage = "$errorLoadingDestinations ${e.message.orEmpty()}"
            } finally {
                isLoading = false
            }
        }
    }

    LaunchedEffect(Unit) {
        loadDestinations()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = PlannerColors.Background,
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PlannerColors.Background)
        ) {
            val stepModifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 22.dp,
                    end = 22.dp,
                    top = 48.dp,
                    bottom = padding.calculateBottomPadding() + 20.dp
                )

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                errorMessage != null -> {
                    Column(
                        modifier = stepModifier,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = errorMessage ?: unknownError,
                            color = PlannerColors.Error,
                            fontSize = 16.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        PlannerPrimaryButton(
                            text = retryText,
                            onClick = {
                                step = 1
                                loadDestinations()
                            }
                        )
                    }
                }

                else -> {
                    when (step) {
                        1 -> Box(modifier = stepModifier) {
                            PlannerWelcomeStep(
                                onStartClick = { step = 2 }
                            )
                        }

                        2 -> PlannerFormStep(
                            destinations = destinations,
                            selectedDestination = selectedDestination,
                            onDestinationChange = { selectedDestination = it },
                            transportType = transportType,
                            onTransportChange = { transportType = it },
                            date = date,
                            onDateChange = { date = it },
                            people = people,
                            onPeopleChange = { people = it },
                            bottomPadding = padding.calculateBottomPadding(),
                            onSearchClick = {
                                val destination = selectedDestination

                                if (destination == null) {
                                    errorMessage = errorSelectDestination
                                    return@PlannerFormStep
                                }

                                coroutineScope.launch {
                                    isLoading = true
                                    errorMessage = null

                                    try {

                                        val destinationName = destination.getNombre(currentLanguage)
                                        routes = repository.getRoutesByDestination(destinationName)
                                        step = 3
                                    } catch (e: Exception) {
                                        routes = emptyList()
                                        errorMessage = "$errorLoadingAvailability ${e.message.orEmpty()}"
                                    } finally {
                                        isLoading = false
                                    }
                                }
                            }
                        )

                        3 -> PlannerAvailabilityStep(

                            destination = selectedDestination?.getNombre(currentLanguage) ?: defaultDestination,
                            routes = routes,
                            onOptionSelected = {
                                selectedRoute = it
                                step = 4
                            },
                            onBackClick = {
                                step = 2
                                routes = emptyList()
                            },
                            bottomPadding = padding.calculateBottomPadding()
                        )

                        4 -> PlannerConfirmationStep(
                            destination = selectedDestination?.getNombre(currentLanguage) ?: defaultDestination,
                            route = selectedRoute,
                            date = date,
                            people = people,
                            onFinishClick = {
                                step = 1
                                selectedRoute = null
                                routes = emptyList()
                            }
                        )
                    }
                }
            }
        }
    }
}