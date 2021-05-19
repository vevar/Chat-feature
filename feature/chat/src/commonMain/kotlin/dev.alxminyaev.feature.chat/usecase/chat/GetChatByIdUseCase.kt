package dev.alxminyaev.feature.chat.usecase.chat

import com.alxminyaev.tool.error.exceptions.NotFoundException
import dev.alxminyaev.feature.chat.model.Chat
import dev.alxminyaev.feature.chat.repository.ChatRepository

class GetChatByIdUseCase(
    private val chatRepository: ChatRepository
) {

    suspend fun invoke(id: Long): Chat {
        return chatRepository.findById(id) ?: throw NotFoundException("Чат с id=${id} не найден")
    }
}