package com.example.coyhaiquebc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.coyhaiquebc.navigation.NavGraph
import com.example.coyhaiquebc.ui.theme.SlateLight
import androidx.appcompat.app.AppCompatActivity
import com.example.coyhaiquebc.utils.LanguageManager
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            LanguageManager.init(this)
            LanguageManager.applySavedLanguage()

        setContent {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = SlateLight
                ) {
                    NavGraph()
                }
            }
        }
}


