package com.example.coyhaiquebc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.coyhaiquebc.data.model.TestConnectionDto
import com.example.coyhaiquebc.data.remote.SupabaseClientProvider
import com.example.coyhaiquebc.navigation.NavGraph
import com.example.coyhaiquebc.ui.theme.CoyhaiqueBCTheme
import com.example.coyhaiquebc.ui.theme.SlateLight
import io.github.jan.supabase.postgrest.from

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CoyhaiqueBCTheme {

                LaunchedEffect(Unit) {
                    try {
                        val result = SupabaseClientProvider.client
                            .from("test_connection")
                            .select()
                            .decodeList<TestConnectionDto>()

                        Log.d("SUPABASE_TEST", "Conexión exitosa: $result")
                    } catch (e: Exception) {
                        Log.e("SUPABASE_TEST", "Error conectando a Supabase", e)
                    }
                }

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