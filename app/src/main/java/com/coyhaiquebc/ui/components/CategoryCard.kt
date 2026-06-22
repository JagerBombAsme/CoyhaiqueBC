package com.coyhaiquebc.ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.coyhaiquebc.R
import com.coyhaiquebc.data.model.Category

@Composable
fun CategoryCard(category: Category, onClick: () -> Unit) {
    val configuration = LocalConfiguration.current
    val currentLanguage = configuration.locales[0].language

    val textColor = Color.White.copy(alpha = 0.9f)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(28.dp))
            .clickable { onClick() }
    ) {
        Image(
            painter = painterResource(id = category.imagen),
            contentDescription = category.getNombre(currentLanguage),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        GradientOverlay(
            brush = Brush.horizontalGradient(
                colors = listOf(
                    Color.Black.copy(alpha = 0.72f),
                    Color.Black.copy(alpha = 0.38f),
                    Color.Transparent
                )
            )
        )
        GradientOverlay(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Transparent,
                    Color.Black.copy(alpha = 0.45f)
                )
            )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 22.dp, end = 72.dp, bottom = 20.dp)
        ) {
            Text(
                text = category.getNombre(currentLanguage),
                color = textColor,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = category.getDescripcion(currentLanguage),
                color = textColor.copy(alpha = 0.8f),
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 18.sp
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 18.dp, bottom = 18.dp)
                .size(34.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White.copy(alpha = 0.18f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "›",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun GradientOverlay(brush: Brush) {
    Box(modifier = Modifier.fillMaxSize().background(brush))
}