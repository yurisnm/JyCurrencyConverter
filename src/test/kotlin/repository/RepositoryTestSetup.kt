package repository

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import resources.schemas.TransactionSchema

/**
 * Configure a temp repository connected to Hikari to be used as test.
 * It has the same HikariConfig as the application.
 */
open class RepositoryTestSetup {

    @BeforeEach
    fun setup() {
        /**
         * Creates the jdbc from zero for each test.
         */
        Database.connect(this.hikari())
        transaction {
            SchemaUtils.create(TransactionSchema)
        }
    }

    @AfterEach
    fun tearDown() {
        /**
         * Deletes the DB after each test.
         */
        transaction {
            SchemaUtils.drop(TransactionSchema)
        }
    }

    private fun hikari(): HikariDataSource {
        /**
         * Configures HikariCP with the same configuration as the Application.
         */
        val config = HikariConfig()
        config.driverClassName = "org.h2.Driver"
        config.jdbcUrl = "jdbc:h2:mem:test"
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }
}
