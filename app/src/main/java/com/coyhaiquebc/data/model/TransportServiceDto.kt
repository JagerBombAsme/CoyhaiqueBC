package com.coyhaiquebc.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TransportServiceDto(
    val id: String,
    @SerialName("nombre_es")
    val nombreEs: String,
    @SerialName("nombre_en")
    val nombreEn: String,
    @SerialName("tipo_es")
    val tipoEs: String,
    @SerialName("tipo_en")
    val tipoEn: String,
    @SerialName("descripcion_es")
    val descripcionEs: String? = null,
    @SerialName("descripcion_en")
    val descripcionEn: String? = null,
    val capacity: Int? = null,
    val phone: String? = null,
    val website: String? = null,
    @SerialName("image_url")
    val imageUrl: String? = null,
    @SerialName("is_active")
    val isActive: Boolean? = true
) {
    fun getNombre(language: String): String {
        return if (language == "en") nombreEn else nombreEs
    }

    fun getTipo(language: String): String {
        return if (language == "en") tipoEn else tipoEs
    }

    fun getDescripcion(language: String): String {
        return if (language == "en") descripcionEn ?: "" else descripcionEs ?: ""
    }
}
