package dev.alxminyaev.feature.chat.usecase.chat

import dev.alxminyaev.feature.chat.model.Chat
import dev.alxminyaev.feature.chat.model.DataLimit
import dev.alxminyaev.feature.chat.model.SideOfChat
import dev.alxminyaev.feature.chat.repository.ChatRepository

class GetChatsForUserUseCase(
    private val chatRepository: ChatRepository
) {

    suspend fun invoke(user: SideOfChat.User, dataLimit: DataLimit): List<Chat> {
        return chatRepository.findBySide(user, dataLimit)
    }
}