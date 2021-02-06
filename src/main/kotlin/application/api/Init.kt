package application.api

import application.api.controllers.TransactionController
import application.api.errors.HandlerError
import io.javalin.Javalin
import org.jetbrains.exposed.sql.Database
import org.koin.core.KoinComponent
import org.koin.core.inject
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import resources.schemas.TransactionSchema

object Init: KoinComponent{

    private val registerController: TransactionController by inject()
    private const val BAD_REQUEST: Int = 404

    fun start(): Javalin {
        Database.connect(hikari())
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(TransactionSchema)
        }

        val port: Int = System.getenv("PORT")?.toIntOrNull()?:7000

        val app = Javalin.create().apply {
            exception(Exception::class.java) {
                e, _ -> e.printStackTrace()
            }
            error(BAD_REQUEST) {
                ctx -> ctx.result("Not found")
            }
        }.start(port)

        app.routes{
            registerController.router()
        }

        app.exception( Exception::class.java, HandlerError::handlerErrorException)

        return app
    }

    private fun hikari(): HikariDataSource{
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:mem:app"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

}