package com.fatec.ativ_kmp_valorant.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ability(
    val id: Int,
    val agentId: Int,
    val name: String,
    val description: String? = null,
)