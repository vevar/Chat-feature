package dev.alxminyaev.feature.chat.model

import com.soywiz.klock.DateTimeTz

data class Message(
    val id: Long,
    val text: String?,
    val dateTime: DateTimeTz,
    val sender: SideOfChat,
    val receiver: SideOfChat
)



