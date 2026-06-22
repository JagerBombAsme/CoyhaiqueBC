package com.coyhaiquebc.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.coyhaiquebc.data.model.PlaceDto
import com.coyhaiquebc.data.repository.PlacesRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceDetailScreen(
    navController: NavController,
    placeId: String
) {
    val repository = remember { PlacesRepository() }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var place by remember { mutableStateOf<PlaceDto?>(null) }
    var isLoading by remember { mutableStateOf(value = true) }
    var isFavorite by remember { mutableStateOf(value = false) }

    val configuration = LocalConfiguration.current
    val appLanguage = configuration.locales[0].language

    LaunchedEffect(placeId) {
        isLoading = true
        place = repository.getPlaceById(placeId)
        isLoading = false
    }

    val iconColor by animateColorAsState(
        targetValue = if (isFavorite) Color(0xFFD32F2F) else Color(0xFF243B55),
        label = "FavColorAnimation"
    )

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when {
                isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = Color(0xFF243B55))
                    }
                }
                place == null -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Button(
                            onClick = { navController.navigateUp() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFE8F2FA),
                                contentColor = Color(0xFF2E7BB4)
                            ),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text("Lugar no encontrado. Volver atrás", fontWeight = FontWeight.Bold)
                        }
                    }
                }
                else -> {
                    val currentPlace = place!!
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFFFDFCFB)),
                        contentPadding = PaddingValues(bottom = 32.dp)
                    ) {
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(400.dp)
                            ) {
                                AsyncImage(
                                    model = currentPlace.imageUrl,
                                    contentDescription = currentPlace.getTitulo(appLanguage),
                                    modifier = Modifier.fillMaxSize(),
                                    contentScale = ContentScale.Crop
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(
                                            Brush.verticalGradient(
                                                colors = listOf(Color.Transparent, Color(0xAA000000)),
                                                startY = 500f
                                            )
                                        )
                                )

                                IconButton(
                                    onClick = { navController.navigateUp() },
                                    modifier = Modifier
                                        .padding(start = 16.dp, top = 48.dp)
                                    .size(44.dp)
                                    .background(Color.White.copy(alpha = 0.85f), CircleShape)
                                    .align(Alignment.TopStart)
                                    .border(BorderStroke(1.dp, Color.White.copy(alpha = 0.3f)), CircleShape)
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Volver",
                                        tint = Color(0xFF1E1E1B)
                                    )
                                }

                                Row(
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .padding(end = 16.dp, top = 48.dp),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    FloatingActionButton(
                                        onClick = {
                                            isFavorite = !isFavorite
                                            coroutineScope.launch {
                                                val message = if (isFavorite) "Añadido a favoritos" else "Eliminado de favoritos"
                                                snackbarHostState.showSnackbar(message)
                                            }
                                        },
                                        containerColor = Color.White.copy(alpha = 0.85f),
                                        contentColor = iconColor,
                                        modifier = Modifier.size(44.dp),
                                        shape = CircleShape,
                                        elevation = FloatingActionButtonDefaults.elevation(0.dp)
                                    ) {
                                        Icon(
                                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                            contentDescription = null
                                        )
                                    }
                                    FloatingActionButton(
                                        onClick = {
                                            coroutineScope.launch {
                                                snackbarHostState.showSnackbar("Enlace compartido")
                                            }
                                        },
                                        containerColor = Color.White.copy(alpha = 0.85f),
                                        contentColor = Color(0xFF243B55),
                                        modifier = Modifier.size(44.dp),
                                        shape = CircleShape,
                                        elevation = FloatingActionButtonDefaults.elevation(0.dp)
                                    ) {
                                        Icon(Icons.Default.Share, null)
                                    }
                                }

                                Column(
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .padding(24.dp)
                                ) {
                                    Surface(
                                        color = Color(0xFF243B55),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text(
                                            text = currentPlace.categorySlug.uppercase(),
                                            color = Color.White,
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            letterSpacing = 1.sp
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = currentPlace.getTitulo(appLanguage),
                                        color = Color.White,
                                        fontSize = 28.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        lineHeight = 34.sp
                                    )
                                }
                            }
                        }

                        item {
                            Column(modifier = Modifier.padding(24.dp)) {
                                Text(
                                    text = currentPlace.getSubtitulo(appLanguage),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF2C3E50)
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Text(
                                    text = currentPlace.getDescripcion(appLanguage),
                                    color = Color(0xFF4A4A4A),
                                    fontSize = 16.sp,
                                    lineHeight = 24.sp
                                )
                                Spacer(modifier = Modifier.height(32.dp))
                                
                                HorizontalDivider(color = Color(0xFFEEEEEE))
                                Spacer(modifier = Modifier.height(24.dp))
                                
                                Text(
                                    text = "Información Adicional",
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF2C3E50)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Ubicado en el corazón de la Patagonia, este lugar ofrece experiencias únicas para visitantes locales e internacionales.",
                                    color = Color(0xFF7F8C8D),
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
