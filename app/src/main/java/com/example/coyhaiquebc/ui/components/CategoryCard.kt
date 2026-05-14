package com.example.coyhaiquebc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coyhaiquebc.data.model.Category
import com.example.coyhaiquebc.ui.theme.*

@Composable
fun CategoryCard(
    category: Category,
    onClick: () -> Unit
) {
    val cardColor = Color(category.colorFondo)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(110.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(cardColor)
            .border(1.dp, SlateBorder, RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = category.nombre,
                    color = CharcoalPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    lineHeight = 22.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = category.descripcion,
                    color = CharcoalMuted,
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(IceSurface)
                    .border(1.dp, IceBorder, RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = category.icono,
                    contentDescription = category.nombre,
                    tint = IceBlue,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}