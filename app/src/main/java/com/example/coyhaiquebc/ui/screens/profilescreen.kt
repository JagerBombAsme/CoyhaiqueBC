package com.example.coyhaiquebc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun ProfileScreen(
    navController: NavController
) {
    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFF1E1E1B))
                .padding(14.dp)
        ) {
            ProfileTopCard()

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                SmallProfileCard(
                    modifier = Modifier.weight(1f)
                )

                ImagePlaceholderCard(
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            WideProfileCard()

            Spacer(modifier = Modifier.weight(1f))

            ProfileBottomBar()
        }
    }
}

@Composable
fun ProfileTopCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(252.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(Color(0xFF2A2A27))
            .padding(18.dp)
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(Color(0xFF1B1B19)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 28.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ProfileStat(number = "1.2 K", label = "Visitados")
            ProfileStat(number = "4.5 K", label = "Favoritos")
        }

        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(104.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8E8E3)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "SB",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2A2A27)
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Diego",
                color = Color.White,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Explorador de Sabores",
                color = Color.White.copy(alpha = 0.55f),
                fontSize = 12.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ProfileButton(
                text = "Editar",
                modifier = Modifier.weight(1f)
            )

            ProfileButton(
                text = "Guardar",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ProfileStat(
    number: String,
    label: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = number,
            color = Color.White.copy(alpha = 0.65f),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = label,
            color = Color.White.copy(alpha = 0.35f),
            fontSize = 10.sp
        )
    }
}

@Composable
fun ProfileButton(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(42.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun SmallProfileCard(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(170.dp)
            .clip(RoundedCornerShape(26.dp))
            .background(Color(0xFFE6E6E1))
            .padding(18.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF2A2A27)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "SB",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Sebastián",
                color = Color(0xFF2A2A27),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Turista local",
                color = Color(0xFF2A2A27).copy(alpha = 0.65f),
                fontSize = 11.sp
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(36.dp)
                .align(Alignment.BottomCenter)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF2A2A27)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Agregar",
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ImagePlaceholderCard(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(170.dp)
            .clip(RoundedCornerShape(26.dp))
            .background(Color(0xFFB9C5C0)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Imagen",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun WideProfileCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(Color(0xFFE6E6E1))
            .padding(18.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Text(
                text = "Actividad reciente",
                color = Color(0xFF2A2A27).copy(alpha = 0.6f),
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Lugares guardados",
                color = Color(0xFF2A2A27),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Ver favoritos",
                color = Color(0xFF2A2A27).copy(alpha = 0.7f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .width(130.dp)
                .height(90.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color(0xFFB9C5C0))
        )
    }
}

@Composable
fun ProfileBottomBar() {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .height(58.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(Color(0xFF0F0F0E))
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            BottomIcon(Icons.Default.Email, true)
            BottomIcon(Icons.Default.Search, false)
            BottomIcon(Icons.Default.FavoriteBorder, false)
            BottomIcon(Icons.Default.Menu, false)
        }
    }
}

@Composable
fun BottomIcon(
    icon: ImageVector,
    selected: Boolean
) {
    Box(
        modifier = Modifier
            .size(42.dp)
            .clip(CircleShape)
            .background(if (selected) Color(0xFFE6E6E1) else Color(0xFF1C1C1A)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (selected) Color(0xFF2A2A27) else Color.White.copy(alpha = 0.55f),
            modifier = Modifier.size(20.dp)
        )
    }
}