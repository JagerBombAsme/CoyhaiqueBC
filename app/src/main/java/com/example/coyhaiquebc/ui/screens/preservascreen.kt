    package com.example.coyhaiquebc.ui.screens
    
    import androidx.compose.foundation.background
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.layout.*
    import androidx.compose.foundation.rememberScrollState
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.foundation.verticalScroll
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.DirectionsBus
    import androidx.compose.material.icons.filled.QrCode
    import androidx.compose.material3.*
    import androidx.compose.runtime.*
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.navigation.NavController
    import com.example.coyhaiquebc.data.model.DestinationDto
    import com.example.coyhaiquebc.data.model.TransportRouteDto
    import com.example.coyhaiquebc.data.repository.PlannerRepository
    import com.example.coyhaiquebc.ui.components.BottomNavBar
    import kotlinx.coroutines.launch
    
    @Composable
    fun PlanificacionScreen(
        navController: NavController
    ) {
        
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
    
        fun loadDestinations() {
            coroutineScope.launch {
                isLoading = true
                errorMessage = null
    
                try {
                    destinations = repository.getDestinations()
                    selectedDestination = destinations.firstOrNull()
                } catch (e: Exception) {
                    errorMessage = "No se pudieron cargar los destinos. ${e.message.orEmpty()}"
                } finally {
                    isLoading = false
                }
            }
        }
    
        LaunchedEffect(Unit) {
            loadDestinations()
        }
    
        Scaffold(
            containerColor = Color(0xFFF6F7F5),
            bottomBar = {
                BottomNavBar(navController = navController)
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF6F7F5))
                    .padding(
                        start = 22.dp,
                        end = 22.dp,
                        top = 48.dp,
                        bottom = padding.calculateBottomPadding() + 20.dp
                    )
            ) {
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
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = errorMessage ?: "Error desconocido",
                                color = Color.Red,
                                fontSize = 16.sp
                            )
    
                            Spacer(modifier = Modifier.height(12.dp))
    
                            PlannerPrimaryButton(
                                text = "Reintentar",
                                onClick = {
                                    step = 1
                                    loadDestinations()
                                }
                            )
                        }
                    }
    
                    else -> {
                        when (step) {
                            1 -> PlannerWelcomeStep(
                                onStartClick = { step = 2 }
                            )
    
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
                                onSearchClick = {
                                    val destination = selectedDestination
    
                                    if (destination == null) {
                                        errorMessage = "Por favor, selecciona un destino."
                                        return@PlannerFormStep
                                    }
    
                                    coroutineScope.launch {
                                        isLoading = true
                                        errorMessage = null
    
                                        try {
                                            routes = repository.getRoutesByDestination(destination.name)
                                            step = 3
                                        } catch (e: Exception) {
                                            routes = emptyList()
                                            errorMessage = "No se pudo cargar la disponibilidad. ${e.message.orEmpty()}"
                                        } finally {
                                            isLoading = false
                                        }
                                    }
                                }
                            )
    
                            3 -> PlannerAvailabilityStep(
                                destination = selectedDestination?.name ?: "Destino",
                                routes = routes,
                                onOptionSelected = {
                                    selectedRoute = it
                                    step = 4
                                },
                                onBackClick = {
                                    step = 2
                                    routes = emptyList()
                                }
                            )
    
                            4 -> PlannerConfirmationStep(
                                destination = selectedDestination?.name ?: "Destino",
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
    
    @Composable
    fun PlannerWelcomeStep(
        onStartClick: () -> Unit
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "¿Ya sabes\ndónde ir?",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 38.sp,
                color = Color(0xFF1E1E1B)
            )
    
            Spacer(modifier = Modifier.height(14.dp))
    
            Text(
                text = "Planifica tu viaje en pocos pasos. Selecciona el destino, tipo de transporte, fecha y cantidad de personas.",
                fontSize = 15.sp,
                lineHeight = 22.sp,
                color = Color(0xFF6D7875)
            )
    
            Spacer(modifier = Modifier.height(34.dp))
    
            PlannerPrimaryButton(
                text = "Comenzar planificación",
                onClick = onStartClick
            )
        }
    }
    
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
        onSearchClick: () -> Unit
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Configura\ntu viaje",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 34.sp,
                color = Color(0xFF1E1E1B)
            )
    
            Spacer(modifier = Modifier.height(24.dp))
    
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
                    .clip(RoundedCornerShape(26.dp))
                    .background(Color(0xFFD8F7E8))
            ) {
                PlannerSegment(
                    text = "Turístico",
                    selected = transportType == "Transporte turístico",
                    modifier = Modifier.weight(1f),
                    onClick = { onTransportChange("Transporte turístico") }
                )
    
                PlannerSegment(
                    text = "Aventura",
                    selected = transportType == "Transporte aventura",
                    modifier = Modifier.weight(1f),
                    onClick = { onTransportChange("Transporte aventura") }
                )
            }
    
            Spacer(modifier = Modifier.height(18.dp))
    
            Text(
                text = "Destinos cargados: ${destinations.size}",
                fontSize = 12.sp,
                color = Color.Red
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
                    title = date,
                    subtitle = "Fecha",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onDateChange(
                            if (date == "12/01/2027") "15/01/2027" else "12/01/2027"
                        )
                    }
                )
    
                PlannerFieldCard(
                    title = people,
                    subtitle = "Personas",
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onPeopleChange(
                            if (people == "2") "3" else "2"
                        )
                    }
                )
            }
    
            Spacer(modifier = Modifier.height(24.dp))
    
            PlannerPrimaryButton(
                text = "Buscar disponibilidad",
                onClick = onSearchClick
            )
        }
    }
    
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
                color = Color(0xFF1E1E1B)
            )
    
            Spacer(modifier = Modifier.height(24.dp))
    
            if (routes.isEmpty()) {
                Text(
                    text = "No hay opciones disponibles para este destino.",
                    color = Color(0xFF6D7875),
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
                color = Color(0xFF1E1E1B)
            )
    
            Spacer(modifier = Modifier.height(12.dp))
    
            Text(
                text = "Presenta este código al subirte al transporte.",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = Color(0xFF6D7875)
            )
    
            Spacer(modifier = Modifier.height(32.dp))
    
            Box(
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(Color.White),
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
                color = Color(0xFF6D7875)
            )
    
            Spacer(modifier = Modifier.height(30.dp))
    
            PlannerPrimaryButton(
                text = "Finalizar",
                onClick = onFinishClick
            )
        }
    }
    
    @Composable
    fun PlannerSegment(
        text: String,
        selected: Boolean,
        modifier: Modifier = Modifier,
        onClick: () -> Unit
    ) {
        Box(
            modifier = modifier
                .fillMaxHeight()
                .clip(RoundedCornerShape(26.dp))
                .background(if (selected) Color(0xFF7E63F4) else Color.Transparent)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = if (selected) Color.White else Color(0xFF1E1E1B),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
    
    @Composable
    fun PlannerSelectableCard(
        title: String,
        subtitle: String,
        selected: Boolean,
        onClick: () -> Unit
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(78.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(if (selected) Color(0xFF8267F2) else Color.White)
                .clickable { onClick() }
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                Text(
                    text = title,
                    color = if (selected) Color.White else Color(0xFF1E1E1B),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
    
                Spacer(modifier = Modifier.height(4.dp))
    
                Text(
                    text = subtitle,
                    color = if (selected) Color.White.copy(alpha = 0.75f) else Color(0xFF6D7875),
                    fontSize = 12.sp,
                    lineHeight = 16.sp
                )
            }
        }
    }
    
    @Composable
    fun PlannerFieldCard(
        title: String,
        subtitle: String,
        modifier: Modifier = Modifier,
        onClick: () -> Unit
    ) {
        Box(
            modifier = modifier
                .height(76.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF8267F2))
                .clickable { onClick() }
                .padding(14.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = subtitle,
                    color = Color.White.copy(alpha = 0.75f),
                    fontSize = 11.sp
                )
    
                Spacer(modifier = Modifier.height(4.dp))
    
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
    
    @Composable
    fun PlannerOptionCard(
        route: TransportRouteDto,
        onClick: () -> Unit
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(82.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFC7B2EE))
                .clickable { onClick() }
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            Text(
                text = route.origin ?: "Coyhaique",
                modifier = Modifier.align(Alignment.TopStart),
                color = Color(0xFF1E1E1B),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
    
            Text(
                text = route.destinationLabel ?: "Destino",
                modifier = Modifier.align(Alignment.TopEnd),
                color = Color(0xFF1E1E1B),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
    
            Icon(
                imageVector = Icons.Default.DirectionsBus,
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center),
                tint = Color(0xFF1E1E1B)
            )
    
            Text(
                text = route.departureTime ?: "--:--",
                modifier = Modifier.align(Alignment.BottomStart),
                color = Color(0xFF1E1E1B),
                fontSize = 11.sp
            )
    
            Text(
                text = route.arrivalTime ?: "--:--",
                modifier = Modifier.align(Alignment.BottomEnd),
                color = Color(0xFF1E1E1B),
                fontSize = 11.sp
            )
    
            Text(
                text = route.priceClp?.let { "$$it CLP" } ?: "Por confirmar",
                modifier = Modifier.align(Alignment.BottomCenter),
                color = Color(0xFF1E1E1B),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
    
    @Composable
    fun PlannerPrimaryButton(
        text: String,
        onClick: () -> Unit
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF7E63F4))
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
    @Composable
    fun PlannerDestinationButton(
        destinations: List<DestinationDto>,
        selectedDestination: DestinationDto?,
        onDestinationChange: (DestinationDto) -> Unit
    ) {
        var expanded by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
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
                        text = "Destino seleccionado",
                        color = Color(0xFF6D7875),
                        fontSize = 11.sp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = selectedDestination?.name
                            ?: if (destinations.isEmpty()) "No hay destinos disponibles"
                            else "Seleccionar destino",
                        color = Color(0xFF1E1E1B),
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
                                    text = destination.name,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )

                                Text(
                                    text = destination.description ?: "Punto turístico disponible",
                                    fontSize = 12.sp,
                                    color = Color(0xFF6D7875)
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