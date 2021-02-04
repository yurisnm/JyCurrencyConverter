package resources.repositores

import domain.repo.Repository
import domain.entities.Transaction
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import resources.extensions.toTransaction
import resources.schemas.TransactionSchema

class TransactionRepository: Repository<Transaction> {
    private val logger = LoggerFactory.getLogger(TransactionRepository::class.java)

    override fun save(entity: Transaction): Transaction = transaction{
        val result = TransactionSchema.insert{
            it[userId] = entity.userId
            it[sourceCurrency] = entity.sourceCurrency
            it[sourceValue] = entity.sourceValue
            it[targetCurrency] = entity.targetCurrency
            it[conversionRate] = entity.conversionRate
            it[dateTime] = entity.dateTime
        }
        logger.info("Save - Entity sucessfully saved.")
        entity.copy(id = result[TransactionSchema.id])
    }

    override fun findAll(): List<Transaction> = transaction {
        TransactionSchema.selectAll().map{
            it.toTransaction()
        }.toList()
    }

    override fun findAllByUserId(userId: String): List<Transaction> = transaction{
        TransactionSchema.select{ TransactionSchema.userId eq userId}.map{
            it.toTransaction()
        }.toList()
    }
}