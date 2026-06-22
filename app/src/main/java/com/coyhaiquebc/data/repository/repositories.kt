package com.coyhaiquebc.data.repository

import com.coyhaiquebc.data.model.PlaceDto
import com.coyhaiquebc.data.remote.SupabaseClientProvider
import io.github.jan.supabase.postgrest.from

class PlacesRepository {

    suspend fun getPlacesByCategory(categorySlug: String): List<PlaceDto> {
        return SupabaseClientProvider.client
            .from("places")
            .select {
                filter {
                    eq("category_slug", categorySlug)
                    eq("is_active", true)
                }
            }
            .decodeList<PlaceDto>()
    }

    suspend fun getPlaceById(id: String): PlaceDto? {
        return try {
            SupabaseClientProvider.client
                .from("places")
                .select {
                    filter {
                        eq("id", id)
                    }
                }
                .decodeSingle<PlaceDto>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}