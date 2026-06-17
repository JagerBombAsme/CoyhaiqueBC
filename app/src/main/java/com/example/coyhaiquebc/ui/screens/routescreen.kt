package com.example.coyhaiquebc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coyhaiquebc.ui.components.BottomNavBar

@Composable
fun AventuraScreen(
    navController: NavController
) {
    var selectedTransport by remember { mutableStateOf("A pie") }

    val points = when (selectedTransport) {
        "A pie" -> listOf(
            TouristPoint("Piedra del Indio", "Punto urbano cercano, ideal para caminata breve."),
            TouristPoint("Plaza de Armas", "Centro de referencia para iniciar recorridos."),
            TouristPoint("Mirador Río Simpson", "Sector accesible para una visita corta.")
        )

        "Transporte público" -> listOf(
            TouristPoint("Reserva Nacional Coyhaique", "Acceso recomendado mediante transporte hacia sectores cercanos."),
            TouristPoint("Museo Regional de Aysén", "Punto cultural accesible desde la ciudad."),
            TouristPoint("Feria Artesanal", "Espacio local para conocer productos y cultura regional.")
        )

        else -> listOf(
            TouristPoint("Reserva Nacional Coyhaique", "Recomendado para acceso en vehículo particular."),
            TouristPoint("Lago Atravesado", "Destino natural cercano a Coyhaique."),
            TouristPoint("Valle Simpson", "Ruta escénica para recorrer en vehículo.")
        )
    }

    Scaffold(
        containerColor = Color(0xFFF6F7F5),
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F7F5))
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 42.dp,
                    bottom = padding.calculateBottomPadding() + 24.dp
                )
        ) {
            Text(
                text = "Rutas y puntos\nde interés",
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 34.sp,
                color = Color(0xFF1E1E1B)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Elige un medio de transporte y descubre los puntos turísticos disponibles según tu forma de recorrido.",
                fontSize = 14.sp,
                lineHeight = 20.sp,
                color = Color(0xFF6D7875)
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "¿Cómo te moverás?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E1E1B)
            )

            Spacer(modifier = Modifier.height(14.dp))

            TransportOptionCard(
                title = "A pie",
                description = "Para recorridos cercanos dentro de la ciudad.",
                icon = Icons.Default.DirectionsWalk,
                selected = selectedTransport == "A pie",
                onClick = { selectedTransport = "A pie" }
            )

            Spacer(modifier = Modifier.height(12.dp))

            TransportOptionCard(
                title = "Transporte público",
                description = "Para acceder a puntos conectados por rutas urbanas o cercanas.",
                icon = Icons.Default.DirectionsBus,
                selected = selectedTransport == "Transporte público",
                onClick = { selectedTransport = "Transporte público" }
            )

            Spacer(modifier = Modifier.height(12.dp))

            TransportOptionCard(
                title = "Vehículo particular",
                description = "Para rutas más flexibles hacia sectores naturales.",
                icon = Icons.Default.DirectionsCar,
                selected = selectedTransport == "Vehículo particular",
                onClick = { selectedTransport = "Vehículo particular" }
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Puntos disponibles",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E1E1B)
            )

            Spacer(modifier = Modifier.height(14.dp))

            points.forEach { point ->
                TouristPointCard(point = point)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

data class TouristPoint(
    val name: String,
    val description: String
)

@Composable
fun TransportOptionCard(
    title: String,
    description: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit
) {
    val background = if (selected) Color(0xFF2F7D75) else Color.White
    val textColor = if (selected) Color.White else Color(0xFF1E1E1B)
    val secondaryColor = if (selected) Color.White.copy(alpha = 0.72f) else Color(0xFF6D7875)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(86.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(background)
            .clickable { onClick() }
            .padding(horizontal = 18.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(46.dp)
                .clip(CircleShape)
                .background(if (selected) Color.White.copy(alpha = 0.16f) else Color(0xFFE9EFED)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column {
            Text(
                text = title,
                color = textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                color = secondaryColor,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
fun TouristPointCard(
    point: TouristPoint
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(44.dp)
                .clip(CircleShape)
                .background(Color(0xFFE9EFED)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = Color(0xFF2F7D75)
            )
        }

        Spacer(modifier = Modifier.width(14.dp))

        Column {
            Text(
                text = point.name,
                color = Color(0xFF1E1E1B),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = point.description,
                color = Color(0xFF6D7875),
                fontSize = 13.sp,
                lineHeight = 17.sp
            )
        }
    }
}