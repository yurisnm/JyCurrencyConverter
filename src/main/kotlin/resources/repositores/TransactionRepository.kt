package resources.repositores

import domain.entities.Transaction
import domain.repo.Repository
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.LoggerFactory
import resources.extensions.toTransaction
import resources.schemas.TransactionSchema

/**
 * Deal with repository possible actions.
 *
 * - Save
 * - List all
 * - List all by id.
 */
class TransactionRepository : Repository<Transaction> {
    private val logger = LoggerFactory.getLogger(TransactionRepository::class.java)

    /**
     *  Inserts new Transaction in the data base.
     *
     *  @property entity[Transaction] transaction that will be inserted in the database.
     */
    override fun save(entity: Transaction): Transaction = transaction {
        val result = TransactionSchema.insert {
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

    /**
     * Get all Transactions from the database
     */
    override fun findAll(): List<Transaction> = transaction {
        TransactionSchema.selectAll().map {
            it.toTransaction()
        }.toList()
    }

    /**
     * Get all Transactions from the database with certain user id.
     *
     * @property userId[String] userId used for getting the transactions related to it.
     */
    override fun findAllByUserId(userId: String): List<Transaction> = transaction {
        TransactionSchema.select { TransactionSchema.userId eq userId }.map {
            it.toTransaction()
        }.toList()
    }
}
