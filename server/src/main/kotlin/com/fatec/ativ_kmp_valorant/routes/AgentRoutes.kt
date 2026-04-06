    package com.fatec.ativ_kmp_valorant.routes

    import com.fatec.ativ_kmp_valorant.model.Agent
    import com.fatec.ativ_kmp_valorant.repository.AgentRepository
    import com.fatec.ativ_kmp_valorant.repository.AbilityRepository
    import io.ktor.http.*
    import io.ktor.server.request.*
    import io.ktor.server.response.*
    import io.ktor.server.routing.*

    fun Route.agentRoutes(
        agentRepository: AgentRepository,
        abilityRepository: AbilityRepository
    ) {

        route("/agents") {

            // GET /agents → lista todos os agentes
            get {
                val agents = agentRepository.getAllAgents()
                call.respond(agents)
            }

            // GET /agents/{id} → busca agente por ID
            get("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@get call.respond(HttpStatusCode.BadRequest)

                val agent = agentRepository.getAgentById(id)
                    ?: return@get call.respond(HttpStatusCode.NotFound)

                call.respond(agent)
            }

            // 🔥 POST /agents → criar agente
            post {
                val agent = call.receive<Agent>()
                val created = agentRepository.createAgent(agent)
                call.respond(HttpStatusCode.Created, created)
            }

            // 🔥 PUT /agents/{id} → atualizar agente
            put("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@put call.respond(HttpStatusCode.BadRequest)

                val agent = call.receive<Agent>()
                val updated = agentRepository.updateAgent(id, agent)

                if (updated) {
                    call.respond(HttpStatusCode.OK)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }

            // 🔥 DELETE /agents/{id} → deletar agente
            delete("/{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@delete call.respond(HttpStatusCode.BadRequest)

                val deleted = agentRepository.deleteAgent(id)

                if (deleted) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }

            // GET /agents/{id}/abilities → habilidades do agente
            get("/{id}/abilities") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@get call.respond(HttpStatusCode.BadRequest)

                val abilities = abilityRepository.getByAgentId(id)
                call.respond(abilities)
            }
        }
    }