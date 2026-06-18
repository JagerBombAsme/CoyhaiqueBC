package com.example.coyhaiquebc.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DestinationDto(
    val id: String,
    val name: String,
    val slug: String? = null,
    val description: String? = null,

    @SerialName("distance_km")
    val distanceKm: Double? = null,

    @SerialName("estimated_time")
    val estimatedTime: String? = null,

    val difficulty: String? = null,

    @SerialName("image_url")
    val imageUrl: String? = null,

    @SerialName("is_active")
    val isActive: Boolean? = null
)