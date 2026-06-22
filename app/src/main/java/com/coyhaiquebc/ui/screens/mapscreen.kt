package com.coyhaiquebc.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.coyhaiquebc.ui.components.BottomNavBar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch

@Composable
fun MapScreen(navController: NavController) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(-45.5700, -72.0660), 12f)
    }

    var searchText by remember { mutableStateOf("") }
    var isSearching by remember { mutableStateOf(false) }

    val sernatur = LatLng(-45.5699325, -72.0675682)
    val plazaArmas = LatLng(-45.5712, -72.0685)
    val coyhaique = LatLng(-45.5700, -72.0660)

    val quickLocations = listOf(
        MapLocation("SERNATUR", sernatur, Icons.Default.Info),
        MapLocation("Plaza de Armas", plazaArmas, Icons.Default.Park),
        MapLocation("Centro", coyhaique, Icons.Default.LocationCity)
    )

    Scaffold(
        containerColor = Color(0xFFF6F7F5),
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(
                    isMyLocationEnabled = hasLocationPermission,
                    mapType = MapType.NORMAL
                ),
                uiSettings = MapUiSettings(
                    zoomControlsEnabled = false,
                    myLocationButtonEnabled = false 
                )
            ) {
                if (isSearching) {
                    Marker(
                        state = MarkerState(position = sernatur),
                        title = "SERNATUR",
                        snippet = "Oficina de Información Turística"
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    color = Color.White,
                    tonalElevation = 4.dp,
                    shadowElevation = 8.dp
                ) {
                    TextField(
                        value = searchText,
                        onValueChange = {
                            searchText = it
                            isSearching = it.isNotEmpty()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text("Buscar destinos, servicios...", color = Color.Gray, fontSize = 15.sp)
                        },
                        leadingIcon = {
                            Icon(Icons.Default.Search, null, tint = Color(0xFF2F7D75))
                        },
                        trailingIcon = {
                            if (searchText.isNotEmpty()) {
                                IconButton(onClick = {
                                    searchText = ""
                                    isSearching = false
                                }) {
                                    Icon(Icons.Default.Close, null, tint = Color.Gray)
                                }
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color(0xFF2F7D75)
                        ),
                        textStyle = TextStyle(color = Color(0xFF1A1A1A), fontSize = 15.sp),
                        singleLine = true
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(horizontal = 4.dp)
                ) {
                    items(quickLocations) { location ->
                        MapQuickChip(
                            label = location.name,
                            icon = location.icon,
                            onClick = {
                                coroutineScope.launch {
                                    cameraPositionState.animate(
                                        CameraUpdateFactory.newLatLngZoom(location.position, 16f)
                                    )
                                }
                                searchText = location.name
                                isSearching = true
                            }
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 16.dp, bottom = 170.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MapFloatingButton(
                    icon = Icons.Default.MyLocation,
                    onClick = {
                        coroutineScope.launch {
                            cameraPositionState.animate(
                                CameraUpdateFactory.newLatLngZoom(coyhaique, 15f)
                            )
                        }
                    }
                )

                MapFloatingButton(
                    icon = Icons.Default.Home,
                    containerColor = Color(0xFF1A1A1A),
                    onClick = {
                        coroutineScope.launch {
                            cameraPositionState.animate(
                                CameraUpdateFactory.newLatLngZoom(coyhaique, 13f)
                            )
                        }
                        searchText = ""
                        isSearching = false
                    }
                )
            }
        }
    }
}

@Composable
fun MapQuickChip(
    label: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 4.dp,
        tonalElevation = 2.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(icon, null, modifier = Modifier.size(16.dp), tint = Color(0xFF2F7D75))
            Text(
                text = label,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A1A1A)
            )
        }
    }
}

@Composable
fun MapFloatingButton(
    icon: ImageVector,
    containerColor: Color = Color.White,
    contentColor: Color = if (containerColor == Color.White) Color(0xFF2F7D75) else Color.White,
    onClick: () -> Unit
) {
    LargeFloatingActionButton(
        onClick = onClick,
        modifier = Modifier.size(56.dp),
        shape = CircleShape,
        containerColor = containerColor,
        contentColor = contentColor,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 6.dp)
    ) {
        Icon(icon, null, modifier = Modifier.size(24.dp))
    }
}

data class MapLocation(
    val name: String,
    val position: LatLng,
    val icon: ImageVector
)