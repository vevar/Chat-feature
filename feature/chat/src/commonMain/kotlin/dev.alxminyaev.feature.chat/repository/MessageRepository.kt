package dev.alxminyaev.feature.chat.repository

import dev.alxminyaev.feature.chat.model.DataLimit
import dev.alxminyaev.feature.chat.model.Message
import dev.alxminyaev.feature.chat.model.user.User
import io.ktor.utils.io.*

interface MessageRepository {

    suspend fun save(message: Message, files: Map<String, ByteReadChannel>?): Long

    suspend fun findByChatAndUser(chatId: Long, dataLimit: DataLimit): List<Message>
}