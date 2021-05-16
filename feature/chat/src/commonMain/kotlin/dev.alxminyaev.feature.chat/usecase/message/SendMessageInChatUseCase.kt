package dev.alxminyaev.feature.chat.usecase.message

import com.alxminyaev.tool.error.exceptions.NotFoundException
import com.alxminyaev.tool.error.exceptions.ValidationDataException
import com.soywiz.klock.DateTime
import dev.alxminyaev.feature.chat.model.Message
import dev.alxminyaev.feature.chat.model.SideOfChat
import dev.alxminyaev.feature.chat.model.user.User
import dev.alxminyaev.feature.chat.repository.ChatRepository
import dev.alxminyaev.feature.chat.repository.FileRepository
import dev.alxminyaev.feature.chat.repository.MessageRepository
import dev.alxminyaev.feature.chat.repository.UserRepository
import io.ktor.utils.io.*

class SendMessageInChatUseCase(
    private val messageRepository: MessageRepository,
    private val chatRepository: ChatRepository,
    private val userRepository: UserRepository,
) {

    suspend fun invoke(
        creator: User,
        sender: SideOfChat,
        receiver: SideOfChat,
        text: String? = null,
        files: Map<String, ByteReadChannel>? = null
    ): Long {
        if (text.isNullOrBlank() && (files == null || files.isEmpty())) {
            throw ValidationDataException(message = "Message can't be empty")
        }

        when (sender) {
            is SideOfChat.User -> userRepository.findById(sender.id)
                ?: throw NotFoundException("Пользователь с id=${sender.id} не найден")
            is SideOfChat.Chat -> chatRepository.findById(sender.id)
                ?: throw  NotFoundException("Чат с id=${sender.id} не найден")
        }

        when (receiver) {
            is SideOfChat.User -> throw NotFoundException("Пользователь с id=${receiver.id} не найден")
            is SideOfChat.Chat -> chatRepository.findById(receiver.id)
                ?: throw  NotFoundException("Чат с id=${receiver.id} не найден")
        }


        val message = Message(
            id = 0,
            text = text,
            dateTime = DateTime.nowLocal(),
            creator = creator,
            sender = sender,
            receiver = receiver
        )

        return messageRepository.save(message, files)
    }


}