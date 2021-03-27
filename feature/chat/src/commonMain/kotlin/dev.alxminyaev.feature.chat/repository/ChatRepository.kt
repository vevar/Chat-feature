package dev.alxminyaev.feature.chat.repository

import dev.alxminyaev.feature.chat.model.Chat
import dev.alxminyaev.feature.chat.model.DataLimit
import dev.alxminyaev.feature.chat.model.SideOfChat
import dev.alxminyaev.feature.chat.model.user.User

interface ChatRepository {

    suspend fun save(chat: Chat): Long

    suspend fun findById(id: Long): Chat?

    suspend fun findByUser(sideOfChat: SideOfChat.User, dataLimit: DataLimit): List<Chat>

    suspend fun findByUsersAndOneType(firstUser: Long, secondUser: Long): List<Chat>

    suspend fun addUsersToChat(chat: Chat, users: List<User>)

    suspend fun deleteUsersFromChat(chat: Chat, users: List<User>)


}