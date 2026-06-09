package com.example.coyhaiquebc.ui.screens

import androidx.compose.runtime.Composable
import com.example.coyhaiquebc.R

@Composable
fun AlojamientosScreen(
) {
    val alojamientos = listOf(
        CategoryItem(
            title = "Hotel Dreams",
            subtitle = "Centro de Coyhaique",
            description = "Hotel moderno con buena ubicación, restaurante y servicios turísticos.",
            image = R.drawable.categoria_alojamiento
        ),
        CategoryItem(
            title = "Hostal Patagonia",
            subtitle = "Sector residencial",
            description = "Hostal cómodo para viajeros que buscan una opción económica.",
            image = R.drawable.categoria_alojamiento
        ),
        CategoryItem(
            title = "Cabañas El Bosque",
            subtitle = "Entorno natural",
            description = "Cabañas para familias y turistas que buscan tranquilidad.",
            image = R.drawable.categoria_alojamiento
        )
    )

    CategoryListScreen(
        title = "Encuentra tu\nalojamiento",
        subtitle = "Hoteles, hostales y cabañas disponibles en Coyhaique.",
        searchPlaceholder = "Buscar alojamiento",
        tabs = listOf("Todos", "Hotel", "Hostal", "Cabañas"),
        selectedTab = "Todos",
        featuredItems = alojamientos,
        popularItems = alojamientos,
        onItemClick = { alojamiento ->
            // Próximo paso: navegar al detalle del alojamiento
            // navController.navigate(Routes.detail(alojamiento.title))
        }
    )
}