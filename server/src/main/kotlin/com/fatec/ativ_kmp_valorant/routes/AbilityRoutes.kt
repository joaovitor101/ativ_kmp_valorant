package com.fatec.ativ_kmp_valorant.routes

import com.fatec.ativ_kmp_valorant.model.Ability
import com.fatec.ativ_kmp_valorant.repository.AbilityRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.abilityRoutes(abilityRepository: AbilityRepository) {

    route("/abilities") {

        // GET /abilities → listar todas
        get {
            val abilities = abilityRepository.getAll()
            call.respond(abilities)
        }

        // GET /abilities/{id} → buscar por id
        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(HttpStatusCode.BadRequest)

            val ability = abilityRepository.getById(id)
                ?: return@get call.respond(HttpStatusCode.NotFound)

            call.respond(ability)
        }

        // POST /abilities → criar
        post {
            val ability = call.receive<Ability>()
            val created = abilityRepository.create(ability)
            call.respond(HttpStatusCode.Created, created)
        }

        // PUT /abilities/{id} → atualizar
        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@put call.respond(HttpStatusCode.BadRequest)

            val ability = call.receive<Ability>()
            val updated = abilityRepository.update(id, ability)
            call.respond(HttpStatusCode.OK, updated)
        }

        // DELETE /abilities/{id} → deletar
        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@delete call.respond(HttpStatusCode.BadRequest)

            abilityRepository.delete(id)
            call.respond(HttpStatusCode.NoContent)
        }
    }

    // 🔥 ROTA ANINHADA (continua aqui também)
    get("/agents/{id}/abilities") {
        val id = call.parameters["id"]?.toIntOrNull()
            ?: return@get call.respond(HttpStatusCode.BadRequest)

        val abilities = abilityRepository.getByAgentId(id)
        call.respond(abilities)
    }
}