package services

import models.Wishlists
import models.Wishlist
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class WishlistService {

    fun addWishlist(userId: Int): Wishlist {
        // Lógica para adicionar uma nova lista de desejos
        val id = Wishlists.insertAndGetId {
            it[Wishlists.userId] = userId
        }
        return Wishlist(id.value, userId)
    }

    fun getAllWishlistsForUser (userId: Int): List<Wishlist> {
        // Lógica para retornar todas as listas de desejos de um usuário
        return Wishlists.selectAll().where { Wishlists.userId eq userId }.map {
            Wishlist(
                id = it[Wishlists.id].value,
                userId = it[Wishlists.userId]
            )
        }
    }

    fun getWishlistById(id: Int): Wishlist? {
        // Lógica para retornar uma lista de desejos específica
        return Wishlists.selectAll().where { Wishlists.id eq id }.map {
            Wishlist(
                id = it[Wishlists.id].value,
                userId = it[Wishlists.userId]
            )
        }.singleOrNull()
    }

    fun updateWishlist(id: Int, userId: Int) {
        // Lógica para atualizar uma lista de desejos
        Wishlists.update({ Wishlists.id eq id }) {
            it[Wishlists.userId] = userId
        }
    }

    fun deleteWishlist(id: Int) {
        // Lógica para remover uma lista de desejos
        Wishlists.deleteWhere { Wishlists.id eq id }
    }
}