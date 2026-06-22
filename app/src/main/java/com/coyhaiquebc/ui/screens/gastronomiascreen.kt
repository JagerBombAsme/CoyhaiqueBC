package com.coyhaiquebc.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.coyhaiquebc.R
import com.coyhaiquebc.data.model.PlaceDto
import com.coyhaiquebc.navigation.Routes
import com.coyhaiquebc.data.repository.PlacesRepository

@Composable
fun GastronomiaScreen(
    navController: NavController
) {
    val configuration = LocalConfiguration.current
    val currentLanguage = configuration.locales[0].language

    val appLanguage = when (currentLanguage) {
        "en" -> "en"
        else -> "es"
    }

    val repository = remember { PlacesRepository() }

    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var places by remember { mutableStateOf<List<PlaceDto>>(emptyList()) }
    var reloadTrigger by remember { mutableIntStateOf(0) }

    val loadingText = stringResource(R.string.gastronomia_loading)
    val errorText = stringResource(R.string.gastronomia_error)
    val retryText = stringResource(R.string.gastronomia_retry)
    val noDescriptionText = stringResource(R.string.gastronomia_no_description)
    val noTitleText = stringResource(R.string.gastronomia_no_title)
    val defaultSubtitleText = stringResource(R.string.gastronomia_default_subtitle)
    val emptyText = stringResource(R.string.gastronomia_empty)

    val titleText = stringResource(R.string.gastronomia_title)
    val subtitleText = stringResource(R.string.gastronomia_subtitle)
    val searchPlaceholderText = stringResource(R.string.gastronomia_search_placeholder)

    val allTabText = stringResource(R.string.gastronomia_tab_all)
    val restaurantTabText = stringResource(R.string.gastronomia_tab_restaurant)
    val cafeTabText = stringResource(R.string.gastronomia_tab_cafe)
    val traditionalTabText = stringResource(R.string.gastronomia_tab_traditional)

    LaunchedEffect(reloadTrigger) {
        isLoading = true
        errorMessage = null

        try {
            places = repository.getPlacesByCategory("gastronomia")
        } catch (e: Exception) {
            e.printStackTrace()
            errorMessage = errorText
        } finally {
            isLoading = false
        }
    }

    val gastronomia = remember(places, appLanguage, noDescriptionText, noTitleText, defaultSubtitleText) {
        places.map { place ->
            CategoryItem(
                id = place.id,
                title = place.getTitulo(appLanguage) ?: noTitleText,
                subtitle = place.getSubtitulo(appLanguage) ?: defaultSubtitleText,
                description = place.getDescripcion(appLanguage) ?: noDescriptionText,
                imageUrl = place.imageUrl
            )
        }
    }

    when {
        isLoading -> LoadingScreen(loadingText)

        errorMessage != null ->
            ErrorScreen(
                errorMessage = errorMessage ?: errorText,
                retryText = retryText,
                onRetry = { reloadTrigger++ }
            )

        gastronomia.isEmpty() ->
            EmptyScreen(
                message = emptyText,
                retryText = retryText,
                onRetry = { reloadTrigger++ }
            )

        else -> {
            CategoryListScreen(
                title = titleText,
                subtitle = subtitleText,
                searchPlaceholder = searchPlaceholderText,
                tabs = listOf(
                    allTabText,
                    restaurantTabText,
                    cafeTabText,
                    traditionalTabText
                ),
                selectedTab = allTabText,
                featuredItems = gastronomia,
                popularItems = gastronomia,
                onItemClick = { item ->
                    val id = item.id

                    if (id != null) {
                        navController.navigate(Routes.placeDetail(id))
                    } else {
                        println("Error: ID de gastronomía es nulo")
                    }
                },
                onReload = { reloadTrigger++ }
            )
        }
    }
}

@Composable
private fun LoadingScreen(
    loadingText: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = loadingText)
    }
}

@Composable
private fun ErrorScreen(
    errorMessage: String,
    retryText: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = errorMessage)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onRetry) {
            Text(text = retryText)
        }
    }
}

@Composable
private fun EmptyScreen(
    message: String,
    retryText: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = message)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onRetry) {
            Text(text = retryText)
        }
    }
}