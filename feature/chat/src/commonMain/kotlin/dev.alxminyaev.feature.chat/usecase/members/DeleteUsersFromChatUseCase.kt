package dev.alxminyaev.feature.chat.usecase.members

import com.alxminyaev.tool.error.exceptions.NotFoundException
import dev.alxminyaev.feature.chat.repository.ChatRepository
import dev.alxminyaev.feature.chat.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class DeleteUsersFromChatUseCase(
    private val chatRepository: ChatRepository,
    private val userRepository: UserRepository
) {

    suspend fun invoke(chatId: Long, usersIds: List<Long>) {
        withContext(Dispatchers.Main) {
            val chat =
                async { chatRepository.findById(chatId) ?: throw NotFoundException("Чат с id=${chatId} не найден") }
            val users = async(Dispatchers.Default) {
                val foundUsersByIds = userRepository.findByIds(usersIds)
                val foundedUsersIds = foundUsersByIds.map { it.id }
                usersIds.forEach {
                    if (foundedUsersIds.contains(it)) {
                        throw NotFoundException("Пользователь с id=${it} не найден")
                    }
                }
                foundUsersByIds
            }


            chatRepository.deleteUsersFromChat(chat.await(), users.await())
        }

    }
}