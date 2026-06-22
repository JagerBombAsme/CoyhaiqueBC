package com.coyhaiquebc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.coyhaiquebc.R
import com.coyhaiquebc.data.model.homeCategories
import com.coyhaiquebc.ui.components.CategoryCard
import com.coyhaiquebc.ui.components.HomeHeader
import com.coyhaiquebc.ui.theme.*
import com.coyhaiquebc.navigation.Routes
import com.coyhaiquebc.ui.components.BottomNavBar

@Composable
fun HomeScreen(
    navController: NavController
) {

    val configuration = LocalConfiguration.current
    val currentLanguage = configuration.locales[0].language

    var search by remember { mutableStateOf("") }


    val homeTitle = stringResource(R.string.home_title)
    val homeSubtitle =  stringResource(R.string.home_subtitle)
    val categoriesTitle =  stringResource(R.string.home_categories_title)
    val noCategoriesFound = stringResource(R.string.home_no_categories_found)


    val categoriasFiltradas by remember(currentLanguage) {
        derivedStateOf {
            val categories = if (search.isBlank()) {
                homeCategories
            } else {
                homeCategories.filter {
                    it.getNombre(currentLanguage)
                        .contains(search, ignoreCase = true) ||
                            it.getDescripcion(currentLanguage)
                                .contains(search, ignoreCase = true)
                }
            }

            categories.map { category ->
                category.copy(
                    nombre_es = category.getNombre(currentLanguage),
                    descripcion_es = category.getDescripcion(currentLanguage)
                )
            }
        }
    }

    Scaffold(
        containerColor = SlateLight,
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SlateLight)
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 40.dp,
                    bottom = padding.calculateBottomPadding() + 24.dp
                )
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            HomeHeader(
                onProfileClick = {
                    navController.navigate(Routes.PROFILE)
                }
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = homeTitle,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = homeSubtitle,
                color = CharcoalMuted,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = categoriesTitle,
                color = CharcoalPrimary,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            if (categoriasFiltradas.isEmpty()) {
                Text(
                    text = "$noCategoriesFound \"$search\"",
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
}