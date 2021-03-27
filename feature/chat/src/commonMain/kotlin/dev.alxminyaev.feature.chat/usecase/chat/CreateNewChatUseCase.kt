package dev.alxminyaev.feature.chat.usecase.chat

import com.alxminyaev.tool.error.exceptions.ExistException
import com.alxminyaev.tool.error.exceptions.ValidationDataException
import dev.alxminyaev.feature.chat.model.Chat
import dev.alxminyaev.feature.chat.repository.ChatRepository
import dev.alxminyaev.feature.chat.repository.UserRepository

class CreateNewChatUseCase(
    private val chatRepository: ChatRepository,
    private val userRepository: UserRepository
) {

    suspend fun createGroupChat(name: String): Long {
        if (name.isBlank()) {
            throw ValidationDataException(field = "name", message = "Name of chat must be set")
        }
        return chatRepository.save(
            Chat(
                id = 0,
                users = listOf(),
                name = name,
                type = Chat.Type.GROUP,
                userInfo = Chat.UserInfo(0),
                lastMessage = null
            )
        )
    }

    suspend fun invoke(creatorId: Long, usersId: List<Long>, chatType: Chat.Type, name: String? = null): Long {
        if (usersId.isEmpty()) {
            throw ValidationDataException(field = "users", message = "List of users must be set")
        }
        val usersIdsOfChat = usersId.toMutableSet().apply { add(creatorId) }.toList()
        val users = userRepository.findByIds(usersIdsOfChat)
        when (chatType) {
            Chat.Type.ONE_TO_ONE -> {
                if (users.size > 2) {
                    throw ValidationDataException(
                        field = "users",
                        message = "Too much users for chatType=${Chat.Type.ONE_TO_ONE.id}"
                    )
                }
                if (users.size < 2) {
                    throw ValidationDataException(
                        field = "users",
                        message = "Too few users for chatType=${Chat.Type.ONE_TO_ONE.id}"
                    )
                }
                if (users.find { it.id == creatorId } == null) {
                    throw ValidationDataException(
                        field = "users",
                        message = "Creator not found"
                    )
                }
                if (chatRepository.findByUsersAndOneType(users[0].id, users[1].id).isNotEmpty()) {
                    throw ExistException("Чат уже существует")
                }
            }
            Chat.Type.GROUP -> {
                if (name.isNullOrBlank()) {
                    throw ValidationDataException(field = "name", message = "Name of chat must be set")
                }
            }

            else -> throw ValidationDataException(field = "chatType", message = "Incorrect type of chat")
        }


        return chatRepository.save(
            Chat(
                id = 0,
                users = users,
                name = name,
                type = chatType,
                userInfo = Chat.UserInfo(0),
                lastMessage = null
            )
        )
    }

}