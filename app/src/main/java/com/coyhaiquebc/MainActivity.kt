package com.coyhaiquebc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.coyhaiquebc.navigation.NavGraph
import com.coyhaiquebc.ui.theme.SlateLight
import androidx.appcompat.app.AppCompatActivity
import com.coyhaiquebc.utils.LanguageManager
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            _root_ide_package_.com.coyhaiquebc.utils.LanguageManager.init(this)
            _root_ide_package_.com.coyhaiquebc.utils.LanguageManager.applySavedLanguage()

        setContent {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = _root_ide_package_.com.coyhaiquebc.ui.theme.SlateLight
                ) {
                    _root_ide_package_.com.coyhaiquebc.navigation.NavGraph()
                }
            }
        }
}


