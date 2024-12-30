package services

import models.Genre
import models.Genres
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class GenreService {

    fun addGenre(genre: Genre): Genre {
        // Lógica para adicionar um gênero ao banco de dados
        Genres.insert {
            it[name] = genre.name
        }
        return genre
    }

    fun getAllGenres(): List<Genre> {
        // Lógica para retornar todos os gêneros
        return Genres.selectAll().map {
            Genre(
                id = it[Genres.id].value,
                name = it[Genres.name]
            )
        }
    }

    fun getGenreById(id: Int): Genre? {
        // Lógica para retornar um gênero específico
        return Genres.selectAll().where { Genres.id eq id }.map {
            Genre(
                id = it[Genres.id].value,
                name = it[Genres.name]
            )
        }.singleOrNull()
    }

    fun updateGenre(genre: Genre) {
        // Lógica para atualizar um gênero
        Genres.update({ Genres.id eq genre.id }) {
            it[name] = genre.name
        }
    }

    fun deleteGenre(id: Int) {
        // Lógica para remover um gênero
        Genres.deleteWhere { Genres.id eq id }
    }
}