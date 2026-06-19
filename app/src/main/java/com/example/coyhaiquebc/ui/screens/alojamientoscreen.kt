package com.example.coyhaiquebc.ui.screens

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
import com.example.coyhaiquebc.R
import com.example.coyhaiquebc.data.model.PlaceDto
import com.example.coyhaiquebc.data.repository.PlacesRepository

@Composable
fun AlojamientosScreen(
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

    val loadingText = stringResource(R.string.alojamientos_loading)
    val errorText = stringResource(R.string.alojamientos_error)
    val retryText = stringResource(R.string.alojamientos_retry)
    val noDescriptionText = stringResource(R.string.alojamientos_no_description)
    val noTitleText = stringResource(R.string.alojamientos_no_title)
    val defaultSubtitleText = stringResource(R.string.alojamientos_default_subtitle)
    val emptyText = stringResource(R.string.alojamientos_empty)

    val titleText = stringResource(R.string.alojamientos_title)
    val subtitleText = stringResource(R.string.alojamientos_subtitle)
    val searchPlaceholderText = stringResource(R.string.alojamientos_search_placeholder)

    val allTabText = stringResource(R.string.alojamientos_tab_all)
    val hotelTabText = stringResource(R.string.alojamientos_tab_hotel)
    val hostalTabText = stringResource(R.string.alojamientos_tab_hostal)
    val cabanasTabText = stringResource(R.string.alojamientos_tab_cabanas)

    LaunchedEffect(reloadTrigger) {
        isLoading = true
        errorMessage = null

        try {
            places = repository.getPlacesByCategory("alojamientos")
        } catch (e: Exception) {
            e.printStackTrace()
            errorMessage = errorText
        } finally {
            isLoading = false
        }
    }

    val alojamientos = remember(places, appLanguage, noDescriptionText, noTitleText, defaultSubtitleText) {
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

        alojamientos.isEmpty() ->
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
                    hotelTabText,
                    hostalTabText,
                    cabanasTabText
                ),
                selectedTab = allTabText,
                featuredItems = alojamientos,
                popularItems = alojamientos,
                onItemClick = { alojamiento ->
                    val id = alojamiento.id

                    if (id != null) {
                        navController.navigate("alojamiento_detail/$id")
                    } else {
                        println("Error: ID de alojamiento es nulo")
                    }

                }
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