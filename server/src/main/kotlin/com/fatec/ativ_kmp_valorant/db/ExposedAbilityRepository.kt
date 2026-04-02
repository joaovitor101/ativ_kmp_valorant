package com.fatec.ativ_kmp_valorant.db

import com.fatec.ativ_kmp_valorant.model.Ability
import com.fatec.ativ_kmp_valorant.repository.AbilityRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
class ExposedAbilityRepository : AbilityRepository {

    // Converter ResultRow → Ability
    private fun ResultRow.toAbility() = Ability(
        id = this[Abilities.id].value,
        agentId = this[Abilities.agentId].value,
        name = this[Abilities.name],
        description = this[Abilities.description]
    )

    override suspend fun getAll(): List<Ability> = newSuspendedTransaction {
        Abilities.selectAll().map { it.toAbility() }
    }

    override suspend fun getById(id: Int): Ability? = newSuspendedTransaction {
        Abilities.selectAll()
            .where { Abilities.id eq id }
            .map { it.toAbility() }
            .singleOrNull()
    }

    override suspend fun getByAgentId(agentId: Int): List<Ability> = newSuspendedTransaction {
        Abilities.selectAll()
            .where { Abilities.agentId eq agentId }
            .map { it.toAbility() }
    }

    override suspend fun create(ability: Ability): Ability = newSuspendedTransaction {
        val insert = Abilities.insert {
            it[agentId] = ability.agentId
            it[name] = ability.name
            it[description] = ability.description
        }
        insert.resultedValues!!.first().toAbility()
    }

    override suspend fun update(id: Int, ability: Ability): Ability = newSuspendedTransaction {
        Abilities.update({ Abilities.id eq id }) {
            it[agentId] = ability.agentId
            it[name] = ability.name
            it[description] = ability.description
        }

        Abilities.selectAll()
            .where { Abilities.id eq id }
            .map { it.toAbility() }
            .single()
    }

    override suspend fun delete(id: Int) = newSuspendedTransaction {
        Abilities.deleteWhere { Abilities.id eq id }
    }
}