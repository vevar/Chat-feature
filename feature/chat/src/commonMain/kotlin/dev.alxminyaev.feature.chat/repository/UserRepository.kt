package dev.alxminyaev.feature.chat.repository

import dev.alxminyaev.feature.chat.model.SideOfChat

interface UserRepository {

    suspend fun findById(id: Long): SideOfChat.User?
}