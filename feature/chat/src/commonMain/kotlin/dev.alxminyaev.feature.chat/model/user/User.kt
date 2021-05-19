package dev.alxminyaev.feature.chat.model.user

import dev.alxminyaev.feature.chat.api.models.UserResponse


data class User(
    val id: Long,
    val profile: Profile?,
)

fun User.toApi(): UserResponse {
    return UserResponse(
        id = id,
        profile = profile?.toApi() ?: throw IllegalStateException()
    )
}

