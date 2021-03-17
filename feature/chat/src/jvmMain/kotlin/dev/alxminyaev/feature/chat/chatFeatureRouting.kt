package dev.alxminyaev.feature.chat

import dev.alxminyaev.feature.chat.api.apis.ChatApi
import dev.alxminyaev.feature.chat.api.apis.MessageApi
import io.ktor.application.*
import io.ktor.locations.*
import io.ktor.routing.*

@KtorExperimentalLocationsAPI
fun Application.chatFeatureRouting() {
    routing {
        route("/chat") {
            ChatApi()
            MessageApi()
        }
    }

}
