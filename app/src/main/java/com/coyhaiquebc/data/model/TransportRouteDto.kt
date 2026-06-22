package com.coyhaiquebc.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransportRouteDto(
    val id: String,

    @SerialName("origen_es")
    val origenEs: String? = null,

    @SerialName("origen_en")
    val origenEn: String? = null,

    @SerialName("destino_es")
    val destinoEs: String? = null,

    @SerialName("destino_en")
    val destinoEn: String? = null,

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
) {

    fun getOrigen(language: String): String {
        return when (language) {
            "en" -> origenEn ?: origenEs ?: ""
            else -> origenEs ?: origenEn ?: ""
        }
    }

    fun getDestino(language: String): String {
        return when (language) {
            "en" -> destinoEn ?: destinoEs ?: ""
            else -> destinoEs ?: destinoEn ?: ""
        }
    }

    fun getPriceFormatted(): String {
        return priceClp?.let { "$$it CLP" } ?: "Por confirmar"
    }

    fun getPriceFormatted(language: String): String {
        val priceText = when (language) {
            "en" -> "To be confirmed"
            else -> "Por confirmar"
        }
        return priceClp?.let { "$$it CLP" } ?: priceText
    }
}