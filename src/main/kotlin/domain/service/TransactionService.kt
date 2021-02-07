package domain.service

import domain.entities.Transaction
import domain.repo.Repository
import org.slf4j.LoggerFactory
import java.lang.Exception

/**
 * All operations the repository will be able to do.
 *
 * @property transactionRepository[Repository] repository in which the transaction will be.
 */
class TransactionService(
    private val transactionRepository: Repository<Transaction>
) : Service<Transaction> {

    private val logger = LoggerFactory.getLogger(TransactionService::class.java)

    /**
     * Check Service documentations.
     */
    override fun save(entity: Transaction): Transaction = try {
        transactionRepository.save(entity).also {
            logger.info("Transaction successfully saved.")
        }
    } catch (ex: Exception) {
        logger.error("Transaction Error- $ex")
        throw Exception("Transaction Error - Entity not saved")
    }

    /**
     * Check Service documentations.
     */
    override fun findAll(): List<Transaction> {
        return transactionRepository.findAll()
    }

    /**
     * Check Service documentations.
     */
    override fun findAllByUserId(userId: String): List<Transaction> {
        return transactionRepository.findAllByUserId(userId)
    }
}
