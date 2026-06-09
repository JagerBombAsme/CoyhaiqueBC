package com.example.coyhaiquebc.data.remote

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object SupabaseClientProvider {
    val client = createSupabaseClient(
        supabaseUrl = "https://mijtzoqfuqqxymexewoh.supabase.co",
        supabaseKey = "sb_publishable_0rWbty5pFicV8RIuz77o6A_8fYGeUSw"
    ) {
        install(Postgrest)
        install(Storage)
    }
}