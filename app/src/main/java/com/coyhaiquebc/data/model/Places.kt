package com.coyhaiquebc.data.model

    import kotlinx.serialization.SerialName
    import kotlinx.serialization.Serializable

    @Serializable
    data class PlaceDto(
        val id: String,
        @SerialName("category_slug")
        val categorySlug: String,
        @SerialName("titulo_es")
        val tituloEs: String,
        @SerialName("titulo_en")
        val tituloEn: String,
        @SerialName("subtitulo_es")
        val subtituloEs: String? = null,
        @SerialName("subtitulo_en")
        val subtituloEn: String? = null,
        @SerialName("descripcion_es")
        val descripcionEs: String? = null,
        @SerialName("descripcion_en")
        val descripcionEn: String? = null,
        @SerialName("image_url")
        val imageUrl: String? = null,
        @SerialName("is_featured")
        val isFeatured: Boolean = false,
        @SerialName("is_popular")
        val isPopular: Boolean = false,
        @SerialName("is_active")
        val isActive: Boolean = true
    ) {

        fun getTitulo(language: String): String {
            return when (language) {
                "en" -> tituloEn.ifEmpty { tituloEs }
                else -> tituloEs
            }
        }

        fun getSubtitulo(language: String): String {
            return when (language) {
                "en" -> subtituloEn ?: subtituloEs ?: ""
                else -> subtituloEs ?: subtituloEn ?: ""
            }
        }

        fun getDescripcion(language: String): String {
            return when (language) {
                "en" -> descripcionEn ?: descripcionEs ?: ""
                else -> descripcionEs ?: descripcionEn ?: ""
            }
        }
    }