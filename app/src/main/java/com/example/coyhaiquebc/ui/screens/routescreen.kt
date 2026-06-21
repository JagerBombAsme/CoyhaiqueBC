package com.example.coyhaiquebc.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.coyhaiquebc.R
import com.example.coyhaiquebc.ui.components.BottomNavBar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Dash
import com.google.android.gms.maps.model.Gap
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AventuraScreen(
    navController: NavController
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val hasLocationPermission = remember {
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    val allCategory = stringResource(R.string.aventura_category_all)
    val adventureCategory = stringResource(R.string.aventura_category_adventure)
    val foodCategory = stringResource(R.string.aventura_category_food)
    val natureCategory = stringResource(R.string.aventura_category_nature)
    val familyCategory = stringResource(R.string.aventura_category_family)

    val difficultyLow = stringResource(R.string.difficulty_low)
    val difficultyMedium = stringResource(R.string.difficulty_medium)
    val difficultyHigh = stringResource(R.string.difficulty_high)

    var selectedCategory by remember { mutableStateOf("") }
    var selectedRoute by remember { mutableStateOf<RouteItem?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var showMap by remember { mutableStateOf(false) }
    var isMapReady by remember { mutableStateOf(false) }

    LaunchedEffect(allCategory) {
        if (selectedCategory.isBlank()) {
            selectedCategory = allCategory
        }
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(-45.5700, -72.0660),
            12f
        )
    }

    val categories = listOf(
        allCategory,
        adventureCategory,
        foodCategory,
        natureCategory,
        familyCategory
    )

    val routes = getRoutes(
        adventureCategory = adventureCategory,
        foodCategory = foodCategory,
        natureCategory = natureCategory,
        familyCategory = familyCategory,
        difficultyLow = difficultyLow,
        difficultyMedium = difficultyMedium,
        difficultyHigh = difficultyHigh
    )

    val filteredRoutes = routes.filter { route ->
        val matchesCategory = selectedCategory == allCategory || route.category == selectedCategory

        val matchesSearch = searchQuery.isBlank() ||
                route.name.contains(searchQuery, ignoreCase = true) ||
                route.description.contains(searchQuery, ignoreCase = true)

        matchesCategory && matchesSearch
    }

    suspend fun moveCameraToLocation(location: LatLng, zoom: Float) {
        try {
            cameraPositionState.animate(
                CameraUpdateFactory.newLatLngZoom(location, zoom)
            )
        } catch (e: Exception) {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(location, zoom)
        }
    }

    LaunchedEffect(selectedRoute) {
        selectedRoute?.let { route ->
            if (showMap && isMapReady) {
                delay(300)
                moveCameraToLocation(route.location, 14f)
            }
        }
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
                    start = 20.dp,
                    end = 20.dp,
                    top = 42.dp,
                    bottom = padding.calculateBottomPadding()
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = stringResource(R.string.aventura_title),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 34.sp,
                    color = Color(0xFF1E1E1B)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = stringResource(R.string.aventura_subtitle),
                    fontSize = 15.sp,
                    lineHeight = 21.sp,
                    color = Color(0xFF6D7875)
                )

                Spacer(modifier = Modifier.height(18.dp))

                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text(stringResource(R.string.aventura_search_placeholder))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null
                        )
                    },
                    trailingIcon = {
                        if (searchQuery.isNotBlank()) {
                            IconButton(
                                onClick = { searchQuery = "" }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = null
                                )
                            }
                        }
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(18.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF2F7D75),
                        unfocusedBorderColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(14.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories) { category ->
                        FilterChip(
                            selected = selectedCategory == category,
                            onClick = {
                                selectedCategory = category
                                selectedRoute = null
                                isMapReady = false
                            },
                            label = {
                                Text(
                                    text = category,
                                    fontWeight = if (selectedCategory == category) {
                                        FontWeight.Bold
                                    } else {
                                        FontWeight.Normal
                                    }
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = Color(0xFF2F7D75),
                                selectedLabelColor = Color.White,
                                containerColor = Color.White,
                                labelColor = Color(0xFF1E1E1B)
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(
                            R.string.aventura_routes_available,
                            filteredRoutes.size
                        ),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E1E1B)
                    )

                    TextButton(
                        onClick = {
                            showMap = !showMap
                            if (showMap) {
                                coroutineScope.launch {
                                    delay(300)
                                    moveCameraToLocation(
                                        LatLng(-45.5700, -72.0660),
                                        12f
                                    )
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = if (showMap) Icons.Default.Close else Icons.Default.Map,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = if (showMap) {
                                stringResource(R.string.aventura_hide_map)
                            } else {
                                stringResource(R.string.aventura_show_map)
                            }
                        )
                    }
                }

                if (showMap) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp)
                            .padding(vertical = 10.dp),
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        GoogleMap(
                            modifier = Modifier.fillMaxSize(),
                            cameraPositionState = cameraPositionState,
                            properties = MapProperties(
                                isMyLocationEnabled = hasLocationPermission
                            ),
                            onMapLoaded = {
                                isMapReady = true

                                selectedRoute?.let { route ->
                                    coroutineScope.launch {
                                        delay(300)
                                        moveCameraToLocation(route.location, 14f)
                                    }
                                }
                            }
                        ) {
                            val coyhaiqueCenter = LatLng(-45.5700, -72.0660)

                            Marker(
                                state = MarkerState(position = coyhaiqueCenter),
                                title = "Coyhaique",
                                snippet = "Centro de la ciudad",
                                icon = BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_BLUE
                                )
                            )

                            selectedRoute?.let { route ->
                                Polyline(
                                    points = listOf(coyhaiqueCenter, route.location),
                                    color = Color(0xFF2F7D75),
                                    width = 4f,
                                    pattern = listOf(Dash(30f), Gap(20f))
                                )
                            }

                            filteredRoutes.forEach { route ->
                                val isSelected = selectedRoute == route

                                Marker(
                                    state = MarkerState(position = route.location),
                                    title = route.name,
                                    snippet = route.category,
                                    icon = if (isSelected) {
                                        BitmapDescriptorFactory.defaultMarker(
                                            BitmapDescriptorFactory.HUE_GREEN
                                        )
                                    } else {
                                        BitmapDescriptorFactory.defaultMarker(
                                            BitmapDescriptorFactory.HUE_RED
                                        )
                                    },
                                    onClick = {
                                        selectedRoute = route

                                        if (isMapReady) {
                                            coroutineScope.launch {
                                                delay(250)
                                                moveCameraToLocation(route.location, 14f)
                                            }
                                        }

                                        true
                                    }
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (filteredRoutes.isEmpty()) {
                    EmptyRoutesState()
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                        contentPadding = PaddingValues(bottom = 24.dp)
                    ) {
                        items(
                            items = filteredRoutes,
                            key = { it.id }
                        ) { route ->
                            RouteCard(
                                route = route,
                                onClick = {
                                    selectedRoute = route
                                    showMap = true

                                    if (isMapReady) {
                                        coroutineScope.launch {
                                            delay(300)
                                            moveCameraToLocation(route.location, 14f)
                                        }
                                    }
                                }
                            )
                        }
                    }
                }
            }

            selectedRoute?.let { route ->
                ModalBottomSheet(
                    onDismissRequest = {
                        selectedRoute = null
                    },
                    containerColor = Color.White,
                    shape = RoundedCornerShape(
                        topStart = 28.dp,
                        topEnd = 28.dp
                    )
                ) {
                    RouteDetailContent(
                        route = route,
                        onOpenMap = {
                            showMap = true

                            if (isMapReady) {
                                coroutineScope.launch {
                                    delay(300)
                                    moveCameraToLocation(route.location, 14f)
                                }
                            }

                            selectedRoute = null
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun getRoutes(
    adventureCategory: String,
    foodCategory: String,
    natureCategory: String,
    familyCategory: String,
    difficultyLow: String,
    difficultyMedium: String,
    difficultyHigh: String
): List<RouteItem> {
    return listOf(
        RouteItem(
            id = 1,
            name = stringResource(R.string.route_miradores_name),
            description = stringResource(R.string.route_miradores_description),
            category = adventureCategory,
            duration = "3 horas",
            difficulty = difficultyMedium,
            stops = 4,
            location = LatLng(-45.519759364582185, -72.09609880228778),
            imageRes = 0,
            rating = 4.5,
            stopsList = listOf(
                stringResource(R.string.route_miradores_stop_1),
                stringResource(R.string.route_miradores_stop_2),
                stringResource(R.string.route_miradores_stop_3),
                stringResource(R.string.route_miradores_stop_4)
            )
        ),
        RouteItem(
            id = 7,
            name = stringResource(R.string.route_lenneros_name),
            description = stringResource(R.string.route_lenneros_description),
            category = natureCategory,
            duration = "30 min",
            difficulty = difficultyLow,
            stops = 4,
            location = LatLng(-45.4242, -72.0480),
            imageRes = 0,
            rating = 4.2,
            stopsList = listOf(
                stringResource(R.string.route_lenneros_stop_1),
                stringResource(R.string.route_lenneros_stop_2),
                stringResource(R.string.route_lenneros_stop_3),
                stringResource(R.string.route_lenneros_stop_4)
            )
        ),
        RouteItem(
            id = 8,
            name = stringResource(R.string.route_4lagunas_name),
            description = stringResource(R.string.route_4lagunas_description),
            category = natureCategory,
            duration = "4 horas",
            difficulty = difficultyMedium,
            stops = 4,
            location = LatLng(-45.52084047900688, -71.829670194351980),
            imageRes = 0,
            rating = 4.7,
            stopsList = listOf(
                stringResource(R.string.route_4lagunas_stop_1),
                stringResource(R.string.route_4lagunas_stop_2),
                stringResource(R.string.route_4lagunas_stop_3),
                stringResource(R.string.route_4lagunas_stop_4)
            )
        ),
        RouteItem(
            id = 9,
            name = stringResource(R.string.route_cinchao_name),
            description = stringResource(R.string.route_cinchao_description),
            category = adventureCategory,
            duration = "6 horas",
            difficulty = difficultyHigh,
            stops = 3,
            location = LatLng(-45.51290087531122, -72.02875725145691),
            imageRes = 0,
            rating = 4.9,
            stopsList = listOf(
                stringResource(R.string.route_cinchao_stop_1),
                stringResource(R.string.route_cinchao_stop_2),
                stringResource(R.string.route_cinchao_stop_3)
            )
        ),
        RouteItem(
            id = 10,
            name = stringResource(R.string.route_mackay_name),
            description = stringResource(R.string.route_mackay_description),
            category = adventureCategory,
            duration = "4 horas",
            difficulty = difficultyMedium,
            stops = 3,
            location = LatLng(-45.599014950441294, -72.07256754155097),
            imageRes = 0,
            rating = 4.4,
            stopsList = listOf(
                stringResource(R.string.route_mackay_stop_1),
                stringResource(R.string.route_mackay_stop_2),
                stringResource(R.string.route_mackay_stop_3)
            )
        ),
        RouteItem(
            id = 11,
            name = stringResource(R.string.route_divisadero_name),
            description = stringResource(R.string.route_divisadero_description),
            category = natureCategory,
            duration = "4 horas",
            difficulty = difficultyMedium,
            stops = 2,
            location = LatLng(-45.59804611966424, -72.0851278239362),
            imageRes = 0,
            rating = 4.3,
            stopsList = listOf(
                stringResource(R.string.route_divisadero_stop_1),
                stringResource(R.string.route_divisadero_stop_2)
            )
        ),
        RouteItem(
            id = 2,
            name = stringResource(R.string.route_gastronomica_name),
            description = stringResource(R.string.route_gastronomica_description),
            category = foodCategory,
            duration = "4 horas",
            difficulty = difficultyLow,
            stops = 5,
            location = LatLng(-45.5710, -72.0670),
            imageRes = 0,
            rating = 4.8,
            stopsList = listOf(
                stringResource(R.string.route_gastronomica_stop_1),
                stringResource(R.string.route_gastronomica_stop_2),
                stringResource(R.string.route_gastronomica_stop_3),
                stringResource(R.string.route_gastronomica_stop_4),
                stringResource(R.string.route_gastronomica_stop_5)
            )
        ),
        RouteItem(
            id = 3,
            name = stringResource(R.string.route_natural_name),
            description = stringResource(R.string.route_natural_description),
            category = natureCategory,
            duration = "6 horas",
            difficulty = difficultyHigh,
            stops = 3,
            location = LatLng(-45.4242, -72.0480),
            imageRes = 0,
            rating = 4.7,
            stopsList = listOf(
                stringResource(R.string.route_natural_stop_1),
                stringResource(R.string.route_natural_stop_2),
                stringResource(R.string.route_natural_stop_3)
            )
        ),
        RouteItem(
            id = 4,
            name = stringResource(R.string.route_familiar_name),
            description = stringResource(R.string.route_familiar_description),
            category = familyCategory,
            duration = "2 horas",
            difficulty = difficultyLow,
            stops = 4,
            location = LatLng(-45.5700, -72.0660),
            imageRes = 0,
            rating = 4.3,
            stopsList = listOf(
                stringResource(R.string.route_familiar_stop_1),
                stringResource(R.string.route_familiar_stop_2),
                stringResource(R.string.route_familiar_stop_3),
                stringResource(R.string.route_familiar_stop_4)
            )
        ),
        RouteItem(
            id = 5,
            name = stringResource(R.string.route_aventura_name),
            description = stringResource(R.string.route_aventura_description),
            category = adventureCategory,
            duration = "8 horas",
            difficulty = difficultyHigh,
            stops = 3,
            location = LatLng(-46.0560, -72.1850),
            imageRes = 0,
            rating = 4.9,
            stopsList = listOf(
                stringResource(R.string.route_aventura_stop_1),
                stringResource(R.string.route_aventura_stop_2),
                stringResource(R.string.route_aventura_stop_3)
            )
        ),
        RouteItem(
            id = 6,
            name = stringResource(R.string.route_cultural_name),
            description = stringResource(R.string.route_cultural_description),
            category = familyCategory,
            duration = "3 horas",
            difficulty = difficultyLow,
            stops = 4,
            location = LatLng(-45.5705, -72.0675),
            imageRes = 0,
            rating = 4.2,
            stopsList = listOf(
                stringResource(R.string.route_cultural_stop_1),
                stringResource(R.string.route_cultural_stop_2),
                stringResource(R.string.route_cultural_stop_3),
                stringResource(R.string.route_cultural_stop_4)
            )
        )
    )
}

@Composable
fun RouteCard(
    route: RouteItem,
    onClick: () -> Unit
) {
    val categoryColor = getCategoryColor(route.category)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(134.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(categoryColor, categoryColor.copy(alpha = 0.8f))
                        )
                    )
                    .padding(18.dp)
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .fillMaxWidth(0.8f)
                ) {
                    Text(
                        text = route.name,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White,
                        letterSpacing = (-0.3).sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = route.category.uppercase(),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White.copy(alpha = 0.85f),
                        letterSpacing = 1.sp
                    )
                }

                Surface(
                    modifier = Modifier.align(Alignment.TopEnd),
                    shape = RoundedCornerShape(10.dp),
                    color = Color.White.copy(alpha = 0.2f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "${route.rating}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                // Chips informativos con diseño moderno flotante en la parte inferior de la cabecera
                Row(
                    modifier = Modifier.align(Alignment.BottomStart),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    RouteInfoChip(
                        icon = Icons.Default.AccessTime,
                        text = route.duration
                    )

                    RouteInfoChip(
                        icon = Icons.Default.TrendingUp,
                        text = route.difficulty
                    )

                    RouteInfoChip(
                        icon = Icons.Default.Place,
                        text = "${route.stops} ${stringResource(R.string.aventura_stops)}"
                    )
                }
            }

            Column(
                modifier = Modifier.padding(18.dp)
            ) {
                Text(
                    text = route.description,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    color = Color(0xFF5A6663),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = onClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(44.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2F7D75)
                    ),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(R.string.aventura_view_route),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RouteInfoChip(
    icon: ImageVector,
    text: String
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(Color.White.copy(alpha = 0.18f))
            .padding(horizontal = 9.dp, vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(13.dp)
        )

        Text(
            text = text,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White
        )
    }
}

@Composable
fun RouteDetailContent(
    route: RouteItem,
    onOpenMap: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp, vertical = 18.dp)
    ) {
        Text(
            text = route.name,
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF1E1E1B)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = route.description,
            fontSize = 15.sp,
            lineHeight = 22.sp,
            color = Color(0xFF6D7875)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            DetailPill(
                label = stringResource(R.string.aventura_duration),
                value = route.duration
            )

            DetailPill(
                label = stringResource(R.string.aventura_difficulty),
                value = route.difficulty
            )

            DetailPill(
                label = stringResource(R.string.aventura_rating),
                value = "★ ${route.rating}"
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = stringResource(R.string.aventura_stops_title),
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E1E1B)
        )

        Spacer(modifier = Modifier.height(10.dp))

        route.stopsList.forEachIndexed { index, stop ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF2F7D75)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${index + 1}",
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = stop,
                    fontSize = 15.sp,
                    color = Color(0xFF1E1E1B)
                )
            }
        }

        Spacer(modifier = Modifier.height(22.dp))

        Button(
            onClick = onOpenMap,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2F7D75)
            )
        ) {
            Icon(
                imageVector = Icons.Default.Map,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = stringResource(R.string.aventura_view_on_map),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(18.dp))
    }
}

@Composable
fun DetailPill(
    label: String,
    value: String
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFFF2F6F8))
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        Text(
            text = label,
            fontSize = 11.sp,
            color = Color(0xFF6D7875)
        )

        Text(
            text = value,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E1E1B)
        )
    }
}

@Composable
fun EmptyRoutesState() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.SearchOff,
                contentDescription = null,
                tint = Color(0xFF6D7875),
                modifier = Modifier.size(46.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.aventura_no_routes),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E1E1B)
            )

            Text(
                text = stringResource(R.string.aventura_no_routes_hint),
                fontSize = 14.sp,
                color = Color(0xFF6D7875)
            )
        }
    }
}

@Composable
fun getCategoryColor(category: String): Color {
    return when (category) {
        stringResource(R.string.aventura_category_adventure) -> Color(0xFFE86D5A)
        stringResource(R.string.aventura_category_food) -> Color(0xFFF59E0B)
        stringResource(R.string.aventura_category_nature) -> Color(0xFF2F7D75)
        stringResource(R.string.aventura_category_family) -> Color(0xFF3B82F6)
        else -> Color(0xFF2F7D75)
    }
}

data class RouteItem(
    val id: Int,
    val name: String,
    val description: String,
    val category: String,
    val duration: String,
    val difficulty: String,
    val stops: Int,
    val location: LatLng,
    val imageRes: Int,
    val rating: Double,
    val stopsList: List<String>
)