package com.coyhaiquebc.data.repository

import com.coyhaiquebc.data.remote.SupabaseClientProvider
import com.coyhaiquebc.data.model.DestinationDto
import com.coyhaiquebc.data.model.TransportRouteDto
import io.github.jan.supabase.postgrest.from

class PlannerRepository {

    private val supabaseClient = SupabaseClientProvider.client

    suspend fun getDestinations(): List<DestinationDto> {
        return try {
            supabaseClient
                .from("destinations")
                .select {
                    filter {
                        eq("is_active", true)
                    }
                }
                .decodeList<DestinationDto>()
        } catch (e: Exception) {
            println(" Error al obtener destinos: ${e.message}")
            emptyList()
        }
    }

    suspend fun getRoutesByDestination(destinationName: String): List<TransportRouteDto> {
        return try {
            supabaseClient
                .from("transport_routes")
                .select {
                    filter {
                        or {
                            eq("destino_es", destinationName)
                            eq("destino_en", destinationName)
                        }
                    }
                }
                .decodeList<TransportRouteDto>()
        } catch (e: Exception) {
            println("Error al obtener rutas por destino: ${e.message}")
            emptyList()
        }
    }
}