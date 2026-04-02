package com.fatec.ativ_kmp_valorant.db.migration

import com.fatec.ativ_kmp_valorant.db.Agents
import com.fatec.ativ_kmp_valorant.db.Abilities
import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class V3__Seed_Data : BaseJavaMigration() {

    override fun migrate(context: Context) {
        val safeConnection = FlywayConnection(context.connection)
        val database = Database.connect({ safeConnection })

        transaction(database) {
            seedAgents()
            seedAbilities()
        }
    }

    private fun seedAgents() {
        data class AgentData(
            val name: String,
            val role: String,
            val description: String
        )

        val agents = listOf(
            AgentData("Jett", "Duelist", "Agente ágil focada em mobilidade e eliminações rápidas."),
            AgentData("Sova", "Initiator", "Especialista em rastrear inimigos e revelar posições."),
            AgentData("Brimstone", "Controller", "Controla áreas com smokes e suporte tático."),
            AgentData("Sage", "Sentinel", "Suporte defensivo com cura e controle de área.")
        )

        agents.forEach { a ->
            Agents.insert {
                it[name] = a.name
                it[role] = a.role
                it[description] = a.description
            }
        }
    }

    private fun seedAbilities() {
        data class AbilityData(
            val agentId: Int,
            val name: String,
            val description: String
        )

        val abilities = listOf(

            // Jett (id 1)
            AbilityData(1, "Cloudburst", "Lança uma nuvem de fumaça que bloqueia a visão."),
            AbilityData(1, "Updraft", "Impulsiona Jett para o alto."),
            AbilityData(1, "Tailwind", "Dash rápido na direção do movimento."),
            AbilityData(1, "Blade Storm", "Lança facas letais de alta precisão."),

            // Sova (id 2)
            AbilityData(2, "Recon Bolt", "Dispara um projétil que revela inimigos."),
            AbilityData(2, "Shock Dart", "Projétil que causa dano em área."),
            AbilityData(2, "Owl Drone", "Drone que marca inimigos."),
            AbilityData(2, "Hunter’s Fury", "Dispara raios de energia através do mapa."),

            // Brimstone (id 3)
            AbilityData(3, "Incendiary", "Granada incendiária."),
            AbilityData(3, "Stim Beacon", "Aumenta velocidade de disparo."),
            AbilityData(3, "Sky Smoke", "Cria cortinas de fumaça."),
            AbilityData(3, "Orbital Strike", "Ataque orbital devastador."),

            // Sage (id 4)
            AbilityData(4, "Healing Orb", "Cura aliados."),
            AbilityData(4, "Slow Orb", "Reduz velocidade dos inimigos."),
            AbilityData(4, "Barrier Orb", "Cria uma parede sólida."),
            AbilityData(4, "Resurrection", "Revive um aliado.")
        )

        abilities.forEach { ab ->
            Abilities.insert {
                it[agentId] = ab.agentId
                it[name] = ab.name
                it[description] = ab.description
            }
        }
    }
}