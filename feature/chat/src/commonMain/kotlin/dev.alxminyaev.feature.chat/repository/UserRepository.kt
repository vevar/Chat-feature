package dev.alxminyaev.feature.chat.repository

import dev.alxminyaev.feature.chat.model.user.User

interface UserRepository {

    suspend fun findById(id: Long): User?

    suspend fun findByIds(ids: List<Long>): List<User>

}