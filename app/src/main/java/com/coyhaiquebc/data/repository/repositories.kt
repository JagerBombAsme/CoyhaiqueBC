package com.coyhaiquebc.data.repository

import com.coyhaiquebc.data.model.PlaceDto
import com.coyhaiquebc.data.model.TransportServiceDto
import com.coyhaiquebc.data.remote.SupabaseClientProvider
import io.github.jan.supabase.postgrest.from

class PlacesRepository {

    suspend fun getTransportServices(): List<TransportServiceDto> {
        return try {
            SupabaseClientProvider.client
                .from("transport_services")
                .select {
                    filter {
                        eq("is_active", true)
                    }
                }
                .decodeList<TransportServiceDto>()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getTransportServicesByType(type: String): List<TransportServiceDto> {
        return try {
            SupabaseClientProvider.client
                .from("transport_services")
                .select {
                    filter {
                        eq("tipo_es", type)
                        eq("is_active", true)
                    }
                }
                .decodeList<TransportServiceDto>()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getPlacesByCategory(categorySlug: String): List<PlaceDto> {
        return try {
            println("--- Supabase Fetch: Category=$categorySlug ---")
            val results = SupabaseClientProvider.client
                .from("places")
                .select {
                    filter {
                        eq("category_slug", categorySlug)
                        eq("is_active", true)
                    }
                }
                .decodeList<PlaceDto>()
            println("--- Supabase Success: Fetched ${results.size} items ---")
            results
        } catch (e: Exception) {
            println("--- Supabase Error ($categorySlug): ${e.message} ---")
            e.printStackTrace()
            emptyList()
        }
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

    suspend fun getTransportServiceById(id: String): TransportServiceDto? {
        return try {
            SupabaseClientProvider.client
                .from("transport_services")
                .select {
                    filter {
                        eq("id", id)
                    }
                }
                .decodeSingle<TransportServiceDto>()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}