package com.fatec.ativ_kmp_valorant.repository

import com.fatec.ativ_kmp_valorant.model.Agent

interface AgentRepository {

    suspend fun getAllAgents(): List<Agent>

    suspend fun getAgentById(id: Int): Agent?

    suspend fun createAgent(agent: Agent): Agent

    suspend fun updateAgent(id: Int, agent: Agent): Boolean

    suspend fun deleteAgent(id: Int): Boolean
}