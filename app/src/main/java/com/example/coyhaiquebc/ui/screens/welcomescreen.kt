package com.example.coyhaiquebc.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coyhaiquebc.R
import com.example.coyhaiquebc.navigation.Routes
import com.example.coyhaiquebc.utils.LanguageManager
import android.app.Activity
import androidx.compose.ui.platform.LocalContext
@Composable
fun WelcomeScreen(navController: NavController) {
    val context = LocalContext.current
    val activity = context as? Activity

    val configuration = LocalConfiguration.current
    val currentLanguage = configuration.locales[0].language

    key(currentLanguage) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF6F7F5))
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
                onClick = {
                    LanguageManager.setLanguage("es")
                    activity?.recreate()
                }
            )

            Spacer(modifier = Modifier.height(14.dp))

            LanguageButton(
                text = stringResource(R.string.welcome_language_english),
                selected = currentLanguage == "en",
                onClick = {
                    LanguageManager.setLanguage("en")
                    activity?.recreate()
                }
            )
        }
    }
}

@Composable
fun LanguageButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(if (selected) Color(0xFF2F7D75) else Color.White)
            .clickable { onClick() },
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