package com.example.coyhaiquebc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.foundation.Image
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coyhaiquebc.R
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
                StatsCard(
                    number = "24",
                    label = stringResource(R.string.profile_places_visited),
                    modifier = Modifier.weight(1f)
                )
                StatsCard(
                    number = "12",
                    label = stringResource(R.string.profile_favorites),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            RecentActivityCard()

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun ProfileTopCard(
    onBackClick: () -> Unit,
    navController: NavController
) {
    var profileImageUrl by remember { mutableStateOf<String?>(null) }

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
                contentDescription = stringResource(R.string.profile_back),
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
            ProfileStat(
                number = "1.2K",
                label = stringResource(R.string.profile_visited)
            )
            ProfileStat(
                number = "4.5K",
                label = stringResource(R.string.profile_favorites)
            )
        }


        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 34.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ProfileAvatar(
                imageUrl = profileImageUrl,
                onImageClick = {

                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Diego Del Río",
                color = Color.White,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(R.string.profile_explorer),
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
            ProfileActionButton(
                text = stringResource(R.string.profile_edit),
                icon = Icons.Default.Edit,
                onClick = {

                },
                modifier = Modifier.weight(1f)
            )

            ProfileActionButton(
                text = stringResource(R.string.profile_save),
                icon = Icons.Default.Save,
                onClick = {
                },
                modifier = Modifier.weight(1f)
            )

            ProfileActionButton(
                text = stringResource(R.string.profile_change_language),
                icon = Icons.Default.Language,
                onClick = {
                    navController.navigate(Routes.WELCOME) {
                        popUpTo(Routes.WELCOME) { inclusive = true }
                    }
                },
                modifier = Modifier.weight(1f)
            )
        }
    }
}
@Composable
fun ProfileAvatar(
    imageUrl: String?,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(105.dp)
            .clip(CircleShape)
            .clickable { onImageClick() }
            .background(Color(0xFFE8E8E3)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.comida),
            contentDescription = "Avatar de prueba",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

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
fun ProfileActionButton(
    text: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(36.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Color.White.copy(alpha = 0.10f))
            .clickable { onClick() }
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = Color.White,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = text,
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
fun StatsCard(
    number: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(80.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFE6E6E1))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = number,
                color = Color(0xFF2A2A27),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = label,
                color = Color(0xFF2A2A27).copy(alpha = 0.6f),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun RecentActivityCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFE6E6E1))
            .padding(18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = stringResource(R.string.profile_recent_activity),
                    color = Color(0xFF2A2A27).copy(alpha = 0.6f),
                    fontSize = 12.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.profile_recent_places),
                    color = Color(0xFF2A2A27),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = stringResource(R.string.profile_view_favorites),
                    color = Color(0xFF2F7D75),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {

                    }
                )
            }

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFB9C5C0).copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    tint = Color(0xFF2F7D75),
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    }
}