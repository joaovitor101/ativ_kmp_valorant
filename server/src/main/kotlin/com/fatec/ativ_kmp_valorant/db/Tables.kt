package com.fatec.ativ_kmp_valorant.db

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object Agents : IntIdTable("agents") {
    val name = text("name")
    val role = text("role").nullable() // Duelist, Sentinel, Controller, Initiator
    val description = text("description").nullable()
}

object Abilities : IntIdTable("abilities") {
    val agentId = reference("agent_id", Agents, onDelete = ReferenceOption.CASCADE)
    val name = text("name")
    val description = text("description").nullable()

}