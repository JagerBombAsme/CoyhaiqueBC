package com.example.coyhaiquebc.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.coyhaiquebc.ui.components.BottomNavBar
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
    val coyhaique = LatLng(-45.5700, -72.0660)

    Scaffold(
        containerColor = Color(0xFFF6F7F5),
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp)
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = hasLocationPermission),
                uiSettings = MapUiSettings(zoomControlsEnabled = false)
            ) {
                if (isSearching) {
                    Marker(
                        state = MarkerState(position = sernatur),
                        title = "SERNATUR",
                        snippet = "Oficina de Información Turística"
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp)
                    .padding(top=15.dp)
                    .align(Alignment.TopCenter),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    OutlinedTextField(
                        value = searchText,
                        onValueChange = {
                            searchText = it
                            isSearching = it.isNotEmpty()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text("Buscar destinos, servicios...", color = Color.Gray)
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
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFF2F7D75).copy(alpha = 0.4f),
                            unfocusedBorderColor = Color.Transparent,
                            focusedContainerColor = Color(0xFFF6F7F5),
                            unfocusedContainerColor = Color(0xFFF6F7F5),
                            cursorColor = Color(0xFF2F7D75)
                        ),
                        textStyle = TextStyle(color = Color(0xFF1A1A1A), fontSize = 15.sp),
                        shape = RoundedCornerShape(14.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    cameraPositionState.animate(
                                        CameraUpdateFactory.newLatLngZoom(sernatur, 16f)
                                    )
                                }
                                searchText = "SERNATUR"
                                isSearching = true
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2F7D75)),
                            contentPadding = PaddingValues(horizontal = 12.dp)
                        ) {
                            Icon(Icons.Default.Place, null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "SERNATUR",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }

                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    cameraPositionState.animate(
                                        CameraUpdateFactory.newLatLngZoom(coyhaique, 13f)
                                    )
                                }
                                searchText = ""
                                isSearching = false
                            },
                            modifier = Modifier
                                .width(54.dp)
                                .height(40.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A1A1A)),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Icon(Icons.Default.Home, null, modifier = Modifier.size(18.dp), tint = Color.White)
                        }
                    }
                }
            }
        }
    }
}