package com.example.coyhaiquebc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.coyhaiquebc.navigation.NavGraph
import com.example.coyhaiquebc.ui.theme.SlateLight
import com.example.coyhaiquebc.ui.theme.CoyhaiqueBCTheme
import androidx.navigation.compose.rememberNavController


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            CoyhaiqueBCTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = SlateLight
                ) {
                    NavGraph()
                }

            }
        }

    }

}