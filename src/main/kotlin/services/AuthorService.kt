package services

import models.Author
import models.Authors
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class AuthorService {

    fun addAuthor(author: Author): Author {
        // Lógica para adicionar um autor ao banco de dados
        Authors.insert {
            it[name] = author.name
        }
        return author
    }

    fun getAllAuthors(): List<Author> {
        // Lógica para retornar todos os autores
        return Authors.selectAll().map {
            Author(
                id = it[Authors.id].value,
                name = it[Authors.name]
            )
        }
    }

    fun getAuthorById(id: Int): Author? {
        // Lógica para retornar um autor específico
        return Authors.selectAll().where { Authors.id eq id }.map {
            Author(
                id = it[Authors.id].value,
                name = it[Authors.name]
            )
        }.singleOrNull()
    }

    fun updateAuthor(author: Author) {
        // Lógica para atualizar um autor
        Authors.update({ Authors.id eq author.id }) {
            it[name] = author.name
        }
    }

    fun deleteAuthor(id: Int) {
        // Lógica para remover um autor
        Authors.deleteWhere { Authors.id eq id }
    }
}