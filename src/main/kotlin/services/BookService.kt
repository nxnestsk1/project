package services

import models.Book
import models.Books
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class BookService {

    fun addBook(book: Book): Book {
        // Lógica para adicionar um livro ao banco de dados
        Books.insert {
            it[title] = book.title
            it[authorId] = book.authorId
            it[genreId] = book.genreId
        }
        return book
    }

    fun getAllBooks(): List<Book> {
        // Lógica para retornar todos os livros
        return Books.selectAll().map {
            Book(
                id = it[Books.id].value,
                title = it[Books.title],
                authorId = it[Books.authorId],
                genreId = it[Books.genreId]
            )
        }
    }

    fun getBookById(id: Int): Book? {
        // Lógica para retornar um livro específico
        return Books.selectAll().where { Books.id eq id }.map {
            Book(
                id = it[Books.id].value,
                title = it[Books.title],
                authorId = it[Books.authorId],
                genreId = it[Books.genreId]
            )
        }.singleOrNull()
    }

    fun updateBook(book: Book) {
        // Lógica para atualizar um livro
        Books.update({ Books.id eq book.id }) {
            it[title] = book.title
            it[authorId] = book.authorId
            it[genreId] = book.genreId
        }
    }

    fun deleteBook(id: Int) {
        // Lógica para remover um livro
        Books.deleteWhere { Books.id eq id }
    }
}