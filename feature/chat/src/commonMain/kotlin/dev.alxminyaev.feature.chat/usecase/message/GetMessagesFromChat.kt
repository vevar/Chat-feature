package dev.alxminyaev.feature.chat.usecase.message

import com.alxminyaev.tool.error.exceptions.PermissionException
import dev.alxminyaev.feature.chat.model.DataLimit
import dev.alxminyaev.feature.chat.model.Message
import dev.alxminyaev.feature.chat.model.SideOfChat
import dev.alxminyaev.feature.chat.model.user.User
import dev.alxminyaev.feature.chat.repository.ChatRepository
import dev.alxminyaev.feature.chat.repository.MessageRepository

class GetMessagesFromChat(
    private val messageRepository: MessageRepository,
    private val chatRepository: ChatRepository
) {

    suspend fun invoke(forUser: User, chatId: Long, dataLimit: DataLimit): List<Message> {
        if (chatRepository.findById(chatId)?.users?.contains(User(forUser.id)) != true) {
            throw PermissionException()
        }
        return messageRepository.findByChatAndUser(chatId = chatId, dataLimit = dataLimit)
    }
}