package routes

import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import models.Book


fun Route.bookRouting() {
    route("/books") {
        get {
            // Lógica para retornar todos os livros
            call.respondText("Retornando todos os livros")
        }

        post {
            // Lógica para adicionar um novo livro
            val book = call.receive<Book>()
            call.respondText("Livro adicionado: ${book.title}")
        }

        route("/{id}") {
            get {
                // Lógica para retornar um livro específico
                val id = call.parameters["id"]
                call.respondText("Retornando livro com ID: $id")
            }

            put {
                // Lógica para atualizar um livro
                val id = call.parameters["id"]
                val book = call.receive<Book>()
                call.respondText("Livro atualizado: ${book.title} com ID: $id")
            }

            delete {
                // Lógica para remover um livro
                val id = call.parameters["id"]
                call.respondText("Livro removido com ID: $id")
            }
        }
    }
}