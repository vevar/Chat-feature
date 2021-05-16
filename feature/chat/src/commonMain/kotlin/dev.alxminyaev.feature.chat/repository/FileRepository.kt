package dev.alxminyaev.feature.chat.repository

import dev.alxminyaev.feature.chat.model.FileInfo
import io.ktor.utils.io.*

interface FileRepository {

    suspend fun saveAll(chatId: Long, messageId: Long, map: Map<String, ByteReadChannel>): List<FileInfo>
}