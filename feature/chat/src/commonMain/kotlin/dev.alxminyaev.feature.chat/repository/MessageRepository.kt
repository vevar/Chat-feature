package dev.alxminyaev.feature.chat.repository

import dev.alxminyaev.feature.chat.model.DataLimit
import dev.alxminyaev.feature.chat.model.Message

interface MessageRepository {

    suspend fun save(message: Message): Long

    suspend fun findByChat(chatId: Long, dataLimit: DataLimit): List<Message>
}