package com.example.coyhaiquebc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coyhaiquebc.data.model.homeCategories
import com.example.coyhaiquebc.ui.components.CategoryCard
import com.example.coyhaiquebc.ui.theme.*

@Composable
fun HomeScreen(
    navController: NavController
) {
    var search by remember { mutableStateOf("") }

    val categoriasFiltradas = remember(search) {
        if (search.isBlank()) homeCategories
        else homeCategories.filter {
            it.nombre.contains(search, ignoreCase = true) ||
                    it.descripcion.contains(search, ignoreCase = true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SlateLight)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {
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
            text = "¿Qué haremos hoy?",
                fontFamily = CharisSIL,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 24.sp
            )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = "Elige una categoría para descubrir Coyhaique según tu plan.",
            color = CharcoalMuted,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(22.dp))


        TextField(
            value = search,
            onValueChange = { search = it },
            placeholder = {
                Text(
                    text = "Buscar en Coyhaique...",
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
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))


        Text(
            text = "Categorías",
            color = CharcoalPrimary,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(12.dp))


        if (categoriasFiltradas.isEmpty()) {
            Text(
                text = "No se encontraron categorías para \"$search\"",
                color = CharcoalHint,
                fontSize = 13.sp
            )
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                categoriasFiltradas.forEach { categoria ->
                    CategoryCard(
                        category = categoria,
                        onClick = {
                            navController.navigate(categoria.ruta)
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
