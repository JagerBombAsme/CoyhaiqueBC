package com.example.coyhaiquebc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coyhaiquebc.navigation.Routes
import com.example.coyhaiquebc.ui.components.BottomNavBar

@Composable
fun ProfileScreen(
    navController: NavController
) {
    Scaffold(
        containerColor = Color(0xFF1E1E1B),
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF1E1E1B))
                .padding(
                    start = 14.dp,
                    end = 14.dp,
                    top = 50.dp,
                    bottom = padding.calculateBottomPadding() + 14.dp
                )
        ) {
            ProfileTopCard(
                onBackClick = {
                    navController.popBackStack()
                },
                navController = navController
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                SmallProfileCard(modifier = Modifier.weight(1f))
                ImagePlaceholderCard(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(12.dp))

            WideProfileCard()

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun ProfileTopCard(
    onBackClick: () -> Unit,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .clip(RoundedCornerShape(28.dp))
            .background(Color(0xFF2A2A27))
            .padding(18.dp)
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .background(Color(0xFF1B1B19))
                .clickable { onBackClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                tint = Color.White,
                modifier = Modifier.size(20.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 40.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            ProfileStat(number = "1.2 K", label = "Visitados")
            ProfileStat(number = "4.5 K", label = "Favoritos")
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 34.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE8E8E3)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "DG",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2A2A27)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

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
            ProfileButton1(
                text = "Editar",
                navController = navController,
                modifier = Modifier.weight(1f)
            )

            ProfileButton1(
                text = "Guardar",
                navController = navController,
                modifier = Modifier.weight(1f)
            )

            ProfileButton1(
                text = "Cambiar Idioma de la App",
                navController = navController,
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
fun ProfileButton1(
    text: String,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(42.dp)
            .clip(RoundedCornerShape(22.dp))
            .background(Color.White.copy(alpha = 0.10f))
            .clickable {
                navController.navigate(Routes.WELCOME) {
                    popUpTo(Routes.WELCOME) { inclusive = true }
                }
            },
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
                    text = "DG",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Diego",
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