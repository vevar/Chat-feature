package dev.alxminyaev.feature.chat.usecase.message

import dev.alxminyaev.feature.chat.model.DataLimit
import dev.alxminyaev.feature.chat.model.Message
import dev.alxminyaev.feature.chat.repository.MessageRepository

class GetMessagesFromChat(
    private val messageRepository: MessageRepository
) {

    suspend fun invoke(chatId: Long, dataLimit: DataLimit): List<Message> {
        return messageRepository.findByChat(chatId = chatId, dataLimit = dataLimit)
    }
}