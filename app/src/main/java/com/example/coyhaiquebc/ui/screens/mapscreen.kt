package com.example.coyhaiquebc.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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

    var hasLocationPermission by remember {
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
        containerColor = Color.White,
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(
                    bottom = padding.calculateBottomPadding() + 24.dp
                )
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = hasLocationPermission)
            ) {
                if (isSearching) {
                    Marker(
                        state = MarkerState(position = sernatur),
                        title = "SERNATUR"
                    )
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                        isSearching = it.isNotEmpty()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    placeholder = {
                        Text("Buscar...", color = Color.Black.copy(alpha = 0.7f))
                    },
                    leadingIcon = {
                        Icon(Icons.Default.Search, null, tint = Color.Black)
                    },
                    trailingIcon = {
                        if (searchText.isNotEmpty()) {
                            IconButton(onClick = {
                                searchText = ""
                                isSearching = false
                            }) {
                                Icon(Icons.Default.Close, null, tint = Color.Black)
                            }
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.White.copy(alpha = 0f),
                        cursorColor = Color.White
                    ),
                    textStyle = TextStyle(color = Color.Black, fontSize = 14.sp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                cameraPositionState.animate(
                                    CameraUpdateFactory.newLatLngZoom(sernatur, 18f)
                                )
                            }
                            searchText = "SERNATUR"
                            isSearching = true
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2F7D75))
                    ) {
                        Icon(Icons.Default.Place, null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "SERNATUR",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                cameraPositionState.animate(
                                    CameraUpdateFactory.newLatLngZoom(coyhaique, 12f)
                                )
                            }
                            searchText = ""
                            isSearching = false
                        },
                        modifier = Modifier.weight(0.5f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2F7D75))
                    ) {
                        Icon(Icons.Default.Home, null, modifier = Modifier.size(18.dp), tint = Color.White)
                    }
                }
            }
        }
    }
}