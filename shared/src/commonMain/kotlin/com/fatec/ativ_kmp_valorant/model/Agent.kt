package com.fatec.ativ_kmp_valorant.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// @Serializable: habilita a conversão da classe para/de JSON
@Serializable
data class Agent(
    val id: Int,
    val name: String,
    val role: String, // Duelist, Sentinel...
    val description: String,
)