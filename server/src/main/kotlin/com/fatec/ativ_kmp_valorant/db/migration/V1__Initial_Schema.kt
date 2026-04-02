package com.fatec.ativ_kmp_valorant.db.migration

import com.fatec.ativ_kmp_valorant.db.Agents
import com.fatec.ativ_kmp_valorant.db.Abilities
import com.fatec.ativ_kmp_valorant.model.Agent
import org.flywaydb.core.api.migration.BaseJavaMigration
import org.flywaydb.core.api.migration.Context
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class V1__Initial_Schema : BaseJavaMigration() {
    override fun migrate(context: Context) {
        val safeConnection = FlywayConnection(context.connection)
        val database = Database.connect({ safeConnection })

        transaction(database) {
            // A prioridade e precedência ditam o uso das Chaves Estrangeiras
            SchemaUtils.create(Agents, Abilities)
        }
    }
}