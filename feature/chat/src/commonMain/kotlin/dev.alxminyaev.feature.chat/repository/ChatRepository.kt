package dev.alxminyaev.feature.chat.repository

import dev.alxminyaev.feature.chat.model.Chat
import dev.alxminyaev.feature.chat.model.DataLimit
import dev.alxminyaev.feature.chat.model.SideOfChat

interface ChatRepository {

    suspend fun findById(id: Long): Chat?

    suspend fun findBySide(sideOfChat: SideOfChat, dataLimit: DataLimit): List<Chat>
}