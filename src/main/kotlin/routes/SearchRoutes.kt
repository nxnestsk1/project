package routes

import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.searchRouting() {
    get("/search") {
        val query = call.request.queryParameters["query"]

        call.respondText("Buscando livros com o termo: $query")
    }
}