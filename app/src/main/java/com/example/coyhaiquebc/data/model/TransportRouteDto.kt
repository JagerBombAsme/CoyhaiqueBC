package com.example.coyhaiquebc.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransportRouteDto(
    val id: String,
    val origin: String? = null,

    @SerialName("destination_label")
    val destinationLabel: String? = null,

    @SerialName("departure_time")
    val departureTime: String? = null,

    @SerialName("arrival_time")
    val arrivalTime: String? = null,

    @SerialName("price_clp")
    val priceClp: Int? = null,

    @SerialName("available_seats")
    val availableSeats: Int? = null,

    @SerialName("is_active")
    val isActive: Boolean = true
)