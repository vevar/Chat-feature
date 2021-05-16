package dev.alxminyaev.feature.chat.model

import com.soywiz.klock.DateTimeTz
import dev.alxminyaev.feature.chat.model.user.User

class Message(
    val id: Long,
    val text: String?,
    val dateTime: DateTimeTz,
    val creator: User,
    val sender: SideOfChat,
    val receiver: SideOfChat,
    val files: List<FileInfo> = listOf(),
)





