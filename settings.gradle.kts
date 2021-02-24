rootProject.name = "Chat-feature"



object Modules {

    object Feature {

        private val root = ":feature"

        val chat = "${root}:chat"

        val list: List<String> = listOf(chat)

    }

    object Data {
    }

    object Microservice {

    }


    object Tool {
        const val root = ":Kotlin.Tool:tools"

        object Mpp {
            const val root = "${Tool.root}:mpp"

            const val errorHandling = "${root}:error"
            const val api = "${root}:api"
            const val env = "${root}:env"
            const val domain = "${root}:domain"

            val list = listOf(
                errorHandling,
                api,
                env,
                domain
            )

        }

        object Jvm {
            private const val root = "${Tool.root}:jvm"

            const val webServer = "$root:webServer"
            const val dataBaseManager = "$root:databaseManager"
            const val microservice = "$root:microservice"

            val list = listOf(
                webServer,
                dataBaseManager,
                microservice
            )
        }

        object Js {
            const val root = "${Tool.root}:js"
            const val ui = "$root:ui"
        }
    }


}

Modules.Feature.list.forEach { include(it) }
Modules.Tool.Mpp.list.forEach { include(it) }

include(Modules.Tool.Jvm.webServer)
include(Modules.Tool.Jvm.dataBaseManager)

