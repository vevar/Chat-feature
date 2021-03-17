package dev.alxminyaev.feature.chat.model

sealed class SideOfChat(open val id: Long) {
    data class User(override val id: Long) : SideOfChat(id = id)
    data class Chat(override val id: Long) : SideOfChat(id = id)
}
