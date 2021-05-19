package dev.alxminyaev.feature.chat.model.user

import com.alxminyaev.tool.error.exceptions.ValidationDataException
import kotlinx.serialization.Serializable


@Serializable
data class Profile(
    val firstName: String,
    val lastName: String,
    val middleName: String?,
)

fun Profile.validate() {
    if (firstName.isBlank()) {
        throw ValidationDataException(field = "firstName", message = "Поле не должно быть пустым")
    }
    if (lastName.isBlank()) {
        throw ValidationDataException(field = "lastName", message = "Поле не должно быть пустым")
    }
}

fun Profile.toApi() = dev.alxminyaev.feature.chat.api.models.Profile(
    firstName = firstName,
    lastName = lastName,
    middleName = middleName
)
