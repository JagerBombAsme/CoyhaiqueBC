package com.coyhaiquebc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

data class CategoryItem(
    val title: String,
    val subtitle: String,
    val description: String,
    val imageUrl: String?,
    val id: String
)

@Composable
fun CategoryListScreen(
    title: String,
    subtitle: String,
    searchPlaceholder: String,
    tabs: List<String>,
    selectedTab: String,
    featuredItems: List<CategoryItem>,
    popularItems: List<CategoryItem>,
    onItemClick: (CategoryItem) -> Unit
) {
    var search by remember { mutableStateOf("") }

    val filteredItems = remember(search, featuredItems) {
        if (search.isBlank()) featuredItems
        else featuredItems.filter {
            it.title.contains(search, ignoreCase = true) ||
                    it.subtitle.contains(search, ignoreCase = true) ||
                    it.description.contains(search, ignoreCase = true)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFA))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        CategoryListHeader()

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 32.sp,
            color = Color(0xFF111111)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = subtitle,
            fontSize = 14.sp,
            color = Color(0xFF6F7E7C),
            lineHeight = 20.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        CategorySearchBar(
            search = search,
            onSearchChange = { search = it },
            placeholder = searchPlaceholder
        )

        Spacer(modifier = Modifier.height(20.dp))

        CategoryTabs(
            tabs = tabs,
            selectedTab = selectedTab
        )

        Spacer(modifier = Modifier.height(22.dp))

        Text(
            text = "Recomendados",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111111)
        )

        Spacer(modifier = Modifier.height(14.dp))

        if (filteredItems.isEmpty()) {
            Text(
                text = "No se encontraron resultados para \"$search\"",
                fontSize = 13.sp,
                color = Color.Gray
            )
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(end = 8.dp)
            ) {
                items(filteredItems) { item ->
                    RecommendedCarouselCard(
                        item = item,
                        onClick = { onItemClick(item) }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Popular",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF111111)
        )

        Spacer(modifier = Modifier.height(14.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            popularItems.forEach { item ->
                CategoryListCard(
                    item = item,
                    onClick = { onItemClick(item) }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun CategoryListHeader() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = null,
            tint = Color(0xFF243B55),
            modifier = Modifier.size(18.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = "Coyhaique, Aysén",
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF26413F),
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier
                .size(38.dp)
                .clip(CircleShape)
                .background(Color(0xFFE8EEEE)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.BookmarkBorder,
                contentDescription = "Guardados",
                tint = Color(0xFF243B55)
            )
        }
    }
}

@Composable
fun CategorySearchBar(
    search: String,
    onSearchChange: (String) -> Unit,
    placeholder: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = search,
            onValueChange = onSearchChange,
            placeholder = {
                Text(
                    text = placeholder,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = Color.Gray
                )
            },
            modifier = Modifier
                .weight(1f)
                .height(54.dp)
                .clip(RoundedCornerShape(18.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF0F1F1),
                unfocusedContainerColor = Color(0xFFF0F1F1),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.width(12.dp))

        Box(
            modifier = Modifier
                .size(54.dp)
                .clip(CircleShape)
                .background(Color(0xFF243B55)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Tune,
                contentDescription = "Filtros",
                tint = Color.White
            )
        }
    }
}

@Composable
fun CategoryTabs(
    tabs: List<String>,
    selectedTab: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        tabs.forEach { tab ->
            Text(
                text = tab,
                color = if (tab == selectedTab) Color(0xFF243B55) else Color.Gray,
                fontSize = 14.sp,
                fontWeight = if (tab == selectedTab) FontWeight.Bold else FontWeight.Normal
            )
        }
    }
}

@Composable
fun RecommendedCarouselCard(
    item: CategoryItem,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(180.dp)
            .height(230.dp)
            .clip(RoundedCornerShape(26.dp))
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.72f)
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = item.title,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.subtitle,
                color = Color.White.copy(alpha = 0.86f),
                fontSize = 13.sp
            )
        }
    }
}

@Composable
fun CategoryListCard(
    item: CategoryItem,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = item.imageUrl,
            contentDescription = item.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color.Black.copy(alpha = 0.70f),
                            Color.Black.copy(alpha = 0.35f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, end = 24.dp, bottom = 18.dp)
        ) {
            Text(
                text = item.title,
                color = Color.White,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.subtitle,
                color = Color.White.copy(alpha = 0.86f),
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = item.description,
                color = Color.White.copy(alpha = 0.78f),
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        }
    }
}