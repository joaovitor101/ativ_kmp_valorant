package com.fatec.ativ_kmp_valorant.db

import com.fatec.ativ_kmp_valorant.model.Agent
import com.fatec.ativ_kmp_valorant.repository.AgentRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
class ExposedAgentRepository : AgentRepository {

    private fun ResultRow.toAgent() = Agent(
        id = this[Agents.id].value,
        name = this[Agents.name],
        role = this[Agents.role], // 👈 ADICIONA ISSO
        description = this[Agents.description]
    )

    override suspend fun getAllAgents(): List<Agent> = newSuspendedTransaction {
        Agents.selectAll().map { it.toAgent() }
    }

    override suspend fun getAgentById(id: Int): Agent? = newSuspendedTransaction {
        Agents.selectAll()
            .where { Agents.id eq id }
            .map { it.toAgent() }
            .singleOrNull()
    }

    override suspend fun createAgent(agent: Agent): Agent = newSuspendedTransaction {
        val inserted = Agents.insert {
            it[name] = agent.name
            it[description] = agent.description
        }

        inserted.resultedValues!!.first().toAgent()
    }

    override suspend fun updateAgent(id: Int, agent: Agent): Boolean = newSuspendedTransaction {
        Agents.update({ Agents.id eq id }) {
            it[name] = agent.name
            it[description] = agent.description
        } > 0
    }

    override suspend fun deleteAgent(id: Int): Boolean = newSuspendedTransaction {
        Agents.deleteWhere { Agents.id eq id } > 0
    }
}