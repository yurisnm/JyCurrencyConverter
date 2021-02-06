package application

import application.api.Init
import application.api.config.modulesAll
import io.javalin.Javalin
import org.koin.core.context.startKoin

object Main{

    @JvmStatic
    fun main(args: Array<String>) {
        startApplication()
    }

    fun startApplication(): Javalin{
        startKoin {
            modules(modulesAll)
        }

        return Init.start()
    }
}