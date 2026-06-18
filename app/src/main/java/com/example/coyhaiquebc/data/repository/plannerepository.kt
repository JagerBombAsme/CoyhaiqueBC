package com.example.coyhaiquebc.data.repository

import com.example.coyhaiquebc.data.model.DestinationDto
import com.example.coyhaiquebc.data.model.TransportRouteDto
import com.example.coyhaiquebc.data.remote.SupabaseClientProvider
import io.github.jan.supabase.postgrest.from

class PlannerRepository {

    suspend fun getDestinations(): List<DestinationDto> {
        return SupabaseClientProvider.client
            .from("destinations")
            .select()
            .decodeList<DestinationDto>()
    }

    suspend fun getRoutesByDestination(destinationLabel: String): List<TransportRouteDto> {
        return SupabaseClientProvider.client
            .from("transport_routes")
            .select {
                filter {
                    eq("destination_label", destinationLabel)
                }
            }
            .decodeList<TransportRouteDto>()
    }
}