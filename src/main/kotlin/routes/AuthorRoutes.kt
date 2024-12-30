package routes

import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import models.Author


fun Route.authorRouting() {
    route("/authors") {
        get {
            // Lógica para retornar todos os autores
            call.respondText("Retornando todos os autores")
        }

        post {
            // Lógica para adicionar um novo autor
            val author = call.receive<Author>()
            call.respondText("Autor adicionado: ${author.name}")
        }

        route("/{id}") {
            get {
                // Lógica para retornar um autor específico
                val id = call.parameters["id"]
                call.respondText("Retornando autor com ID: $id")
            }

            put {
                // Lógica para atualizar um autor
                val id = call.parameters["id"]
                val author = call.receive<Author>()
                call.respondText("Autor atualizado: ${author.name} com ID: $id")
            }

            delete {
                // Lógica para remover um autor
                val id = call.parameters["id"]
                call.respondText("Autor removido com ID: $id")
            }
        }
    }
}