package dev.alxminyaev.feature.chat.usecase.message

import com.alxminyaev.tool.error.exceptions.NotFoundException
import com.alxminyaev.tool.error.exceptions.ValidationDataException
import com.soywiz.klock.DateTime
import dev.alxminyaev.feature.chat.model.Message
import dev.alxminyaev.feature.chat.model.SideOfChat
import dev.alxminyaev.feature.chat.repository.ChatRepository
import dev.alxminyaev.feature.chat.repository.MessageRepository
import dev.alxminyaev.feature.chat.repository.UserRepository

class SendMessageInChatUseCase(
    private val messageRepository: MessageRepository,
    private val chatRepository: ChatRepository,
    private val userRepository: UserRepository
) {

    suspend fun invoke(sender: SideOfChat, receiver: SideOfChat, text: String? = null) {
        if (text.isNullOrBlank()) {
            throw ValidationDataException(message = "Message can't be empty")
        }

        when (sender) {
            is SideOfChat.User -> userRepository.findById(sender.id)
                ?: throw NotFoundException("Пользователь с id=${sender.id} не найден")
            is SideOfChat.Common -> chatRepository.findById(sender.id)
                ?: throw  NotFoundException("Чат с id=${sender.id} не найден")
        }

        when (receiver) {
            is SideOfChat.User -> userRepository.findById(sender.id)
                ?: throw NotFoundException("Пользователь с id=${receiver.id} не найден")
            is SideOfChat.Common -> chatRepository.findById(sender.id)
                ?: throw  NotFoundException("Чат с id=${receiver.id} не найден")
        }

        val message = Message(
            id = 0,
            text = text,
            dateTime = DateTime.now().utc,
            sender = sender,
            receiver = receiver
        )

        messageRepository.save(message)
    }
}