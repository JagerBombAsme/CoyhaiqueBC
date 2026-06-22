package com.coyhaiquebc.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.coyhaiquebc.R
import com.coyhaiquebc.data.model.TransportServiceDto
import com.coyhaiquebc.navigation.Routes
import com.coyhaiquebc.data.repository.PlacesRepository
import androidx.compose.material3.*

@Composable
fun TransporteScreen(
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
    var services by remember { mutableStateOf<List<TransportServiceDto>>(emptyList()) }
    var reloadTrigger by remember { mutableIntStateOf(0) }

    val loadingText = stringResource(R.string.transporte_loading)
    val errorText = stringResource(R.string.transporte_error)
    val retryText = stringResource(R.string.transporte_retry)
    val noDescriptionText = stringResource(R.string.transporte_no_description)
    val noTitleText = stringResource(R.string.transporte_no_title)
    val defaultSubtitleText = stringResource(R.string.transporte_default_subtitle)
    val emptyText = stringResource(R.string.transporte_empty)

    val titleText = stringResource(R.string.transporte_title)
    val subtitleText = stringResource(R.string.transporte_subtitle)
    val searchPlaceholderText = stringResource(R.string.transporte_search_placeholder)

    val allTabText = stringResource(R.string.transporte_tab_all)
    val viajesTabText = stringResource(R.string.transporte_tab_viajes)
    val transfersTabText = stringResource(R.string.transporte_tab_transfers)
    val rutasTabText = stringResource(R.string.transporte_tab_rutas)

    LaunchedEffect(reloadTrigger) {
        isLoading = true
        errorMessage = null

        try {
            services = repository.getTransportServices()
        } catch (e: Exception) {
            e.printStackTrace()
            errorMessage = errorText
        } finally {
            isLoading = false
        }
    }

    val transporteItems = remember(services, appLanguage) {
        services.map { service ->
            CategoryItem(
                id = service.id,
                title = service.getNombre(appLanguage).ifEmpty { noTitleText },
                subtitle = service.getTipo(appLanguage).ifEmpty { defaultSubtitleText },
                description = service.getDescripcion(appLanguage).ifEmpty { noDescriptionText },
                imageUrl = service.imageUrl
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

        transporteItems.isEmpty() && !isLoading ->
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
                    viajesTabText,
                    transfersTabText,
                    rutasTabText
                ),
                selectedTab = allTabText,
                featuredItems = transporteItems,
                popularItems = transporteItems,
                onItemClick = { item ->
                    navController.navigate(Routes.placeDetail(item.id))
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
        CircularProgressIndicator(color = Color(0xFF243B55))

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = loadingText, color = Color(0xFF243B55), fontWeight = FontWeight.Medium)
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
        Text(text = errorMessage, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF243B55))
        ) {
            Text(text = retryText, color = Color.White)
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
        Text(text = message, textAlign = TextAlign.Center)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF243B55))
        ) {
            Text(text = retryText, color = Color.White)
        }
    }
}
