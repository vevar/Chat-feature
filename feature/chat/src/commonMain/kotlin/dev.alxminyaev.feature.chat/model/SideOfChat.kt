package dev.alxminyaev.feature.chat.model

sealed class SideOfChat(open val id: Long) {
    data class User(override val id: Long) : SideOfChat(id = id)
    data class Common(override val id: Long) : SideOfChat(id = id)
}
