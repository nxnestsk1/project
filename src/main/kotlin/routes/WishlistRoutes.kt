package routes

import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import models.Book
import models.Wishlist

fun Route.wishlistRouting() {
    route("/wishlists") {
        post {
            // Lógica para criar uma nova lista de desejos
            val wishlist = call.receive<Wishlist>()
            call.respondText("Lista de desejos criada para o usuário ID: ${wishlist.userId}")
        }

        route("/{id}") {
            get {
                // Lógica para retornar os livros em uma lista de desejos
                val id = call.parameters["id"]
                call.respondText("Retornando livros na lista de desejos com ID: $id")
            }

            post("/books") {
                // Lógica para adicionar um livro a uma lista de desejos
                val id = call.parameters["id"]
                val book = call.receive<Book>()
                call.respondText("Livro ${book.title} adicionado à lista de desejos com ID: $id")
            }
        }
    }
}