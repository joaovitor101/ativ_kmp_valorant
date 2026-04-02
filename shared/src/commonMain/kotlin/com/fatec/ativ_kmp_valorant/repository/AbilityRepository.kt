package com.fatec.ativ_kmp_valorant.repository

import com.fatec.ativ_kmp_valorant.model.Ability

// Interface atua como um contrato funcional
interface AbilityRepository {
    suspend fun getAll(): List<Ability>
    suspend fun getById(id: Int): Ability?
    suspend fun getByAgentId(agentId: Int): List<Ability>
    suspend fun create(ability: Ability): Ability
    suspend fun update(id: Int, ability: Ability): Ability
    suspend fun delete(id: Int): Int
}