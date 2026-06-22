package com.coyhaiquebc.ui.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.coyhaiquebc.R
import com.coyhaiquebc.utils.LanguageManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun WelcomeScreen(navController: NavController) {
    val context = LocalContext.current
    val activity = context as? Activity

    val configuration = LocalConfiguration.current
    val currentLanguage = configuration.locales[0].language

    val coroutineScope = rememberCoroutineScope()
    var isChangingLanguage by remember { mutableStateOf(false) }

    fun changeLanguage(language: String) {
        if (isChangingLanguage) return

        isChangingLanguage = true

        coroutineScope.launch {
            delay(1700)
            LanguageManager.setLanguage(language)
            activity?.recreate()
        }
    }

    key(currentLanguage) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F7F5))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 56.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.welcome_title),
                    fontSize = 34.sp,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 38.sp,
                    color = Color(0xFF1E1E1B)
                )

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = stringResource(R.string.welcome_subtitle),
                    fontSize = 15.sp,
                    lineHeight = 22.sp,
                    color = Color(0xFF6D7875)
                )

                Spacer(modifier = Modifier.height(34.dp))

                LanguageButton(
                    text = stringResource(R.string.welcome_language_spanish),
                    selected = currentLanguage == "es",
                    enabled = !isChangingLanguage,
                    onClick = {
                        changeLanguage("es")
                    }
                )

                Spacer(modifier = Modifier.height(14.dp))

                LanguageButton(
                    text = stringResource(R.string.welcome_language_english),
                    selected = currentLanguage == "en",
                    enabled = !isChangingLanguage,
                    onClick = {
                        changeLanguage("en")
                    }
                )
            }

            if (isChangingLanguage) {
                LoadingOverlay()
            }
        }
    }
}

@Composable
fun LanguageButton(
    text: String,
    selected: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(if (selected) Color(0xFF243B55) else Color.White)
            .clickable(enabled = enabled) {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = if (selected) Color.White else Color(0xFF1E1E1B),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun LoadingOverlay() {
    val configuration = LocalConfiguration.current
    val currentLanguage = configuration.locales[0].language

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.35f)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .clip(RoundedCornerShape(18.dp))
                .background(Color.White)
                .padding(horizontal = 24.dp, vertical = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CircularProgressIndicator(
                    color = Color(0xFF243B55),
                    modifier = Modifier.size(48.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))


                if (currentLanguage == "en") {
                    Text(
                        text = "Espera mientras cambiamos el idioma,\nPuede que tu pantalla parpadee,\nTranquilo te cuidamos...",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                } else {
                    Text(
                        text = "Please wait while we change the language,\nYour screen may flicker,\nDon't worry, we've got you covered...",
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}