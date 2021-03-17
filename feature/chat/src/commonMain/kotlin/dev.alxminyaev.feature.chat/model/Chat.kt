package dev.alxminyaev.feature.chat.model

import dev.alxminyaev.feature.chat.model.user.User

data class Chat(
    val id: Long,
    val name: String?,
    val users: List<User>,
    val type: Type,
    val userInfo: UserInfo,
    val lastMessage: Message?
) {
    data class UserInfo(
        val sizeOfUnreadMessages: Long,
    )

    enum class Type(val id: Int) {
        UNKNOWN(0),
        ONE_TO_ONE(1),
        GROUP(2);

        companion object {
            fun getTypeOfChatById(id: Int): Chat.Type {
                return when (id) {
                    ONE_TO_ONE.id -> ONE_TO_ONE
                    GROUP.id -> GROUP
                    else -> UNKNOWN
                }
            }
        }
    }
}
