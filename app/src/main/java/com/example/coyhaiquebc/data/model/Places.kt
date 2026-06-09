package com.example.coyhaiquebc.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlaceDto(
    val id: String,
    val title: String,
    val subtitle: String? = null,
    val description: String? = null,

    @SerialName("image_url")
    val imageUrl: String? = null,

    @SerialName("category_slug")
    val categorySlug: String,

    @SerialName("is_featured")
    val isFeatured: Boolean = false,

    @SerialName("is_popular")
    val isPopular: Boolean = false,

    @SerialName("is_active")
    val isActive: Boolean = true
)