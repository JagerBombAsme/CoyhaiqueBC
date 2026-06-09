package com.example.coyhaiquebc.data.repository

import com.example.coyhaiquebc.data.model.PlaceDto
import com.example.coyhaiquebc.data.remote.SupabaseClientProvider
import io.github.jan.supabase.postgrest.from

class PlacesRepository {

    suspend fun getAlojamientos(): List<PlaceDto> {
        return SupabaseClientProvider.client
            .from("places")
            .select {
                filter {
                    eq("category_slug", "alojamientos")
                    eq("is_active", true)
                }
            }
            .decodeList<PlaceDto>()
    }
}