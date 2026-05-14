package com.example.coyhaiquebc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coyhaiquebc.ui.theme.*
import com.example.coyhaiquebc.data.model.homeCategories

@Composable
fun HomeScreen(
    navController: NavController
) {

    var search by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SlateLight)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = null,
                tint = IceBlue
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Basecamp Coyhaique",
                color = CharcoalMuted,
                fontSize = 13.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))


        Text(
            text = "¿Qué quieres hacer?",
            color = CharcoalPrimary,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Elige una categoría para descubrir Coyhaique según tu plan.",
            color = CharcoalMuted,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(22.dp))

        // ── Buscador ──────────────────────────────────────
        TextField(
            value = search,
            onValueChange = { search = it },
            placeholder = {
                Text(
                    text = "Buscar experiencias en Coyhaique...",
                    color = CharcoalHint
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = CharcoalMuted
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(SlateCard, RoundedCornerShape(16.dp))
                .border(1.dp, SlateBorder, RoundedCornerShape(16.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor   = SlateCard,
                unfocusedContainerColor = SlateCard,
                focusedIndicatorColor   = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor             = IceBlue,
                focusedTextColor        = CharcoalPrimary,
                unfocusedTextColor      = CharcoalPrimary
            )
        )

        Spacer(modifier = Modifier.height(22.dp))


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(SlateCard, RoundedCornerShape(24.dp))
                .border(1.dp, SlateBorder, RoundedCornerShape(24.dp))
                .padding(14.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 14.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(
                            IceSurface,
                            RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Explore,
                        contentDescription = null,
                        tint = IceBlue
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = "Arma tu recorrido",
                        color = CharcoalPrimary,
                        fontSize = 15.sp
                    )

                    Text(
                        text = "Selecciona una rama para comenzar",
                        color = CharcoalMuted,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}