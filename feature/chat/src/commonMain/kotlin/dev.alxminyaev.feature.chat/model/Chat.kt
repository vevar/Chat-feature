package dev.alxminyaev.feature.chat.model

data class Chat(
    val id: Long,
    val name: String,
    val users: List<SideOfChat.User>,
    val userInfo: UserInfo,
    val lastMessage: Message
) {
    data class UserInfo(
        val sizeOfUnreadMessages: Long,
    )
}
