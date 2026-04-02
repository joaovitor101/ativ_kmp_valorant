package com.fatec.ativ_kmp_valorant
import com.fatec.ativ_kmp_valorant.db.DatabaseFactory
import com.fatec.ativ_kmp_valorant.db.ExposedAbilityRepository
import com.fatec.ativ_kmp_valorant.db.ExposedAgentRepository
import com.fatec.ativ_kmp_valorant.routes.abilityRoutes
import com.fatec.ativ_kmp_valorant.routes.agentRoutes
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.plugins.swagger.*


fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) { json() }
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respond(HttpStatusCode.InternalServerError,
                mapOf("error" to (cause.message ?: "Erro interno")))
        }
    }
    DatabaseFactory.init()
    //  Instanciando os repositórios (Exposed = banco real)
    val abilityRepository = ExposedAbilityRepository()
    val agentRepository = ExposedAgentRepository()


    routing{
        get("/") { call.respondText("Serviço Ktor ativo.") }
        get("/health") { call.respondText("OK") }
        //  Swagger UI acessível em /swagger
        swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml")

        abilityRoutes(abilityRepository)
        agentRoutes(agentRepository, abilityRepository)
    }
}