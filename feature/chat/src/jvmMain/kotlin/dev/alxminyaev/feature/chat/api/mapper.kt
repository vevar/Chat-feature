package dev.alxminyaev.feature.chat.api

import com.soywiz.klock.DateFormat
import dev.alxminyaev.feature.chat.api.models.*
import dev.alxminyaev.feature.chat.model.Chat
import dev.alxminyaev.feature.chat.model.FileInfo
import dev.alxminyaev.feature.chat.model.Message
import dev.alxminyaev.feature.chat.model.user.toApi
import java.io.File
import java.time.LocalDateTime
import java.util.logging.Level
import java.util.logging.Logger

const val DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss"

fun FileInfo.toFileResponse(): FileResponse = FileResponse(
    id = id,
    name = File(path).name,
    path = path
)

fun Message.toMessageResponse(): MessageResponse {
    val dateFormat = DateFormat.invoke(DATETIME_FORMAT)

    return MessageResponse(
        id = id,
        text = text,
        dateTime = dateTime.toString(dateFormat),
        sender = sender.id,
        receiver = receiver.id,
        files = files.map { it.toFileResponse() }.toTypedArray()
    )
}

fun List<Message>.toMessageListApi(): MessagesListFromChat {
    return MessagesListFromChat(
        data = this.map { it.toMessageResponse() }.toTypedArray()
    )
}

fun Chat.toChatResponse(): ChatResponse {
    val name = if (name == null) {
        Logger.getGlobal().log(Level.WARNING, "Incorrect value of field name")
        "Name (Error)"
    } else {
        name
    }
    return ChatResponse(
        id = id,
        name = name,
        users = users.map { it.id }.toTypedArray(),
        userInfo = UserInfo(
            sizeOfUnreadMessages = userInfo.sizeOfUnreadMessages
        ),
        lastMessage = lastMessage?.toMessageResponse()
    )
}

fun Chat.toChatDetailResponse(): ChatDetailResponse {
    val name = if (name == null) {
        Logger.getGlobal().log(Level.WARNING, "Incorrect value of field name")
        "Name (Error)"
    } else {
        name
    }
    return ChatDetailResponse(
        id = id,
        name = name,
        users = users.map { it.toApi() }.toTypedArray(),
        userInfo = UserInfo(
            sizeOfUnreadMessages = userInfo.sizeOfUnreadMessages
        ),
        lastMessage = lastMessage?.toMessageResponse()
    )
}

fun List<Chat>.toChatsListResponse(): ChatsList {
    return ChatsList(
        data = this.map { it.toChatResponse() }.toTypedArray()
    )
}