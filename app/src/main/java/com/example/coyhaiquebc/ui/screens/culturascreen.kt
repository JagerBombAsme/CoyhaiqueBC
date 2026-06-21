package com.example.coyhaiquebc.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Audiotrack
import androidx.compose.material.icons.filled.CalendarMonth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coyhaiquebc.R
import com.example.coyhaiquebc.ui.components.BottomNavBar
import kotlinx.coroutines.launch


data class TraditionItem(
    val id: String,
    val name: String,
    val imageRes: Int,
    val detailDescription: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CulturaScreen(navController: NavController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    var isFavorite by remember { mutableStateOf(false) }
    var activeDialogText by remember { mutableStateOf<String?>(null) }


    val traditions = remember {
        listOf(
            TraditionItem("1", "El Mate", R.drawable.comida, "Información detallada, datos históricos y registros fotográficos sobre el ritual del Mate en la Patagonia."),
            TraditionItem("2", "La Jineteada", R.drawable.gaucho, "Información detallada, datos históricos y registros fotográficos sobre las destrezas criollas de La Jineteada."),
            TraditionItem("3", "Chamamé", R.drawable.comida, "Información detallada, datos históricos y registros fotográficos sobre la influencia y compás del Chamamé."),
            TraditionItem("4", "Asado al Palo", R.drawable.gaucho, "Información detallada, datos históricos y registros fotográficos sobre la preparación e hitos del Asado al Palo.")
        )
    }

    val iconColor by animateColorAsState(
        targetValue = if (isFavorite) Color(0xFFD32F2F) else Color(0xFF8B4513),
        label = "FavColorAnimation"
    )

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        bottomBar = { BottomNavBar(navController = navController) }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFDFCFB)),
            contentPadding = paddingValues
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(450.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gaucho),
                        contentDescription = "Gaucho Patagónico",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Color.Transparent, Color(0xAA000000)),
                                    startY = 600f
                                )
                            )
                    )

                    IconButton(
                        onClick = { navController.popBackStack() },
                        modifier = Modifier
                            .padding(start = 16.dp, top = 20.dp)
                            .size(44.dp)
                            .background(Color.White.copy(alpha = 0.85f), CircleShape)
                            .align(Alignment.TopStart)
                            .border(BorderStroke(1.dp, Color.White.copy(alpha = 0.3f)), CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver atrás",
                            tint = Color(0xFF1E1E1B)
                        )
                    }


                    Row(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(end = 16.dp, top = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        FloatingActionButton(
                            onClick = {
                                isFavorite = !isFavorite
                                coroutineScope.launch {
                                    val message = if (isFavorite) "Añadido a tus favoritos" else "Eliminado de tus favoritos"
                                    snackbarHostState.currentSnackbarData?.dismiss()
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
                                    snackbarHostState.currentSnackbarData?.dismiss()
                                    snackbarHostState.showSnackbar("Enlace de cultura copiado al portapapeles")
                                }
                            },
                            containerColor = Color.White.copy(alpha = 0.85f),
                            contentColor = Color(0xFF8B4513),
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
                            color = Color(0xFFE67E22),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = "PATRIMONIO",
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "La cultura del Gaucho",
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }

            item {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "A diferencia del huaso de la zona central de Chile, en Coyhaique el protagonista es el gaucho patagónico o 'pionero'. Su vestimenta típica incluye la boina o boina vasca, el pañuelo al cuello, la faja y las bombachas de campo. Esta herencia se celebra cada año en los festivales costumbristas.",
                        color = Color(0xFF4A4A4A),
                        fontSize = 16.sp,
                        lineHeight = 24.sp
                    )
                    Spacer(modifier = Modifier.height(24.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        CulturalActionCard(
                            title = "Festivales",
                            icon = Icons.Default.CalendarMonth,
                            color = Color(0xFF2E7D32),
                            modifier = Modifier.weight(1f),
                            onClick = {
                                activeDialogText = "Próximos festivales costumbristas e hitos culturales en Coyhaique se desplegarán aquí en la siguiente actualización."
                            }
                        )
                        CulturalActionCard(
                            title = "Música local",
                            icon = Icons.Default.Audiotrack,
                            color = Color(0xFF1565C0),
                            modifier = Modifier.weight(1f),
                            onClick = {
                                activeDialogText = "Muestra de audio folclórico, historias de Chamamé y acordeonistas de la Patagonia próximamente disponibles."
                            }
                        )
                    }
                }
            }

            item {
                Text(
                    text = "Tradiciones de Aysén",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp),
                    color = Color(0xFF2C3E50)
                )
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(
                        items = traditions,
                        key = { it.id }
                    ) { tradition ->
                        TraditionCard(
                            item = tradition,
                            onClick = {
                                activeDialogText = tradition.detailDescription
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }

    activeDialogText?.let { infoText ->
        AlertDialog(
            onDismissRequest = { activeDialogText = null },
            confirmButton = {
                TextButton(onClick = { activeDialogText = null }) {
                    Text("Entendido", color = Color(0xFFE67E22), fontWeight = FontWeight.Bold)
                }
            },
            title = {
                Text(
                    text = "Información Cultural",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF2C3E50)
                )
            },
            text = {
                Text(
                    text = infoText,
                    fontSize = 14.sp,
                    color = Color(0xFF4A4A4A)
                )
            },
            shape = RoundedCornerShape(20.dp),
            containerColor = Color.White
        )
    }
}

@Composable
fun CulturalActionCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .height(100.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.1f)),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(icon, contentDescription = null, tint = color, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, fontWeight = FontWeight.Bold, color = color, fontSize = 14.sp)
        }
    }
}

@Composable
fun TraditionCard(
    item: TraditionItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .width(140.dp)
            .height(180.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Brush.verticalGradient(listOf(Color.Transparent, Color.Black)))
            )
            Text(
                text = item.name,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )
        }
    }
}