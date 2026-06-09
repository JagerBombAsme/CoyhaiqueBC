package com.example.coyhaiquebc.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TestConnectionDto(
    val id: Long,
    val message: String
)