package repository

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import resources.schemas.TransactionSchema


open class RepositoryTestSetup {
    
    @BeforeEach
    fun setup(){
        Database.connect(this.hikari())
        transaction{
            SchemaUtils.create(TransactionSchema)
        }
    }

    @AfterEach
    fun tearDown(){
        transaction {
            SchemaUtils.drop(TransactionSchema)
        }
    }

    private fun hikari(): HikariDataSource{
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