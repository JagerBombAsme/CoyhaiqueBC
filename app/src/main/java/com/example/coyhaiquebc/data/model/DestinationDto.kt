package com.example.coyhaiquebc.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DestinationDto(
    val id: String,

    @SerialName("nombre_es")
    val nombreEs: String,

    @SerialName("nombre_en")
    val nombreEn: String,

    val slug: String? = null,

    @SerialName("descripcion_es")
    val descripcionEs: String? = null,

    @SerialName("descripcion_en")
    val descripcionEn: String? = null,

    @SerialName("distance_km")
    val distanceKm: Double? = null,

    @SerialName("estimated_time")
    val estimatedTime: String? = null,

    val difficulty: String? = null,

    @SerialName("image_url")
    val imageUrl: String? = null,

    @SerialName("is_active")
    val isActive: Boolean? = null
) {

    fun getNombre(language: String): String {
        return when (language) {
            "en" -> nombreEn.ifEmpty { nombreEs }
            else -> nombreEs
        }
    }

    fun getDescripcion(language: String): String {
        return when (language) {
            "en" -> descripcionEn ?: descripcionEs ?: ""
            else -> descripcionEs ?: descripcionEn ?: ""
        }
    }

    fun getDistanceFormatted(): String {
        return distanceKm?.let { "${it} km" } ?: "Distancia no disponible"
    }

    fun getDistanceFormatted(language: String): String {
        val fallback = when (language) {
            "en" -> "Distance not available"
            else -> "Distancia no disponible"
        }
        return distanceKm?.let { "${it} km" } ?: fallback
    }

    fun getDifficultyText(language: String): String {
        return when (difficulty) {
            "Fácil" -> when (language) {
                "en" -> "Easy"
                else -> "Fácil"
            }
            "Media" -> when (language) {
                "en" -> "Medium"
                else -> "Media"
            }
            "Difícil" -> when (language) {
                "en" -> "Hard"
                else -> "Difícil"
            }
            else -> difficulty ?: when (language) {
                "en" -> "Not specified"
                else -> "No especificada"
            }
        }
    }
}