package routes

import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import models.Genre

fun Route.genreRouting() {
    route("/genres") {
        get {
            // Lógica para retornar todos os gêneros
            call.respondText("Retornando todos os gêneros")
        }

        post {
            // Lógica para adicionar um novo gênero
            val genre = call.receive<Genre>()
            call.respondText("Gênero adicionado: ${genre.name}")
        }

        route("/{id}") {
            delete {
                // Lógica para remover um gênero
                val id = call.parameters["id"]
                call.respondText("Gênero removido com ID: $id")
            }
        }
    }
}