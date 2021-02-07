package domain

import domain.entities.Transaction
import domain.service.TransactionService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import repository.TransactionRequestBuilder
import resources.repositores.TransactionRepository

/**
 * Checks all operations made by TransactionService.
 */
class TransactionServiceTest {

    private val transactionRepoMock = mockk<TransactionRepository>()
    private val transactionService = TransactionService(transactionRepoMock)
    private val transactionBuild = TransactionRequestBuilder.build().toModel()

    private fun genTransaction(transaction: Transaction) = transaction.copy()

    @Test
    fun save_transaction() {
        /**
         * Save a transaction only using the service and repository.
         */
        every { transactionRepoMock.save(transactionBuild) } returns this.genTransaction(transactionBuild)
        val transaction = transactionService.save(transactionBuild)

        assertNotNull(transaction)
        verify { transactionRepoMock.save(transactionBuild) }
    }

    @Test
    fun find_all_transaction() {
        /**
         * Checks all transactions only using the service and repository.
         */
        every { transactionRepoMock.findAll() } returns listOf(transactionBuild)
        val transactions = transactionService.findAll()

        assertThat(transactions.size).isGreaterThan(0)
        verify { transactionRepoMock.findAll() }
    }

    @Test
    fun find_all_by_user_id_transaction() {
        /**
         * Checks all transactions by user id only using the service and repository.
         */
        val userId = "user0001"
        every { transactionRepoMock.findAllByUserId(any()) } returns listOf(transactionBuild)
        val transactions = transactionService.findAllByUserId(userId)

        assertThat(transactions.size).isGreaterThan(0)
        verify { transactionRepoMock.findAllByUserId(userId) }
    }
}
