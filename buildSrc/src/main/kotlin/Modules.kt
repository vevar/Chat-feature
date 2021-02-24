import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project

object Modules {


    object Feature {

        const val root = ":feature"

    }

    object Data {
        private const val root = ":sample:data"


        val list = arrayOf<String>()
    }


    object Microservice {
        const val root = ":sample:microservice"


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

