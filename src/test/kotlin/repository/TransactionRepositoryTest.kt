package repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import resources.repositores.TransactionRepository

/**
 * Test all the actions we can make on our TransactionRepository.
 * - Save
 * - List all transactions.
 * - List all transactions by userId.
 */
class TransactionRepositoryTest : RepositoryTestSetup() {

    private val transactionBuild = TransactionRequestBuilder.build().toModel()
    private val transactionRepository = TransactionRepository()

    @Test
    fun transaction_save() {
        /**
         * Checks that we are able to save a transaction into the repository.
         */
        val transaction = transactionRepository.save(transactionBuild)
        assertNotNull(transaction)
    }

    @Test
    fun transaction_all() {
        /**
         * Checks that we can list all transactions in the repository.
         */
        var transactions = transactionRepository.findAll()
        assertThat(transactions.size).isEqualTo(0)
        transactionRepository.save(transactionBuild)

        transactions = transactionRepository.findAll()
        assertThat(transactions.size).isEqualTo(1)
    }

    @Test
    fun transaction_all_by_user_id() {
        /**
         * Checks that we can list all transactions in the repository by it's userId.
         */
        var transactions = transactionRepository.findAllByUserId("user0001")
        assertThat(transactions.size).isEqualTo(0)

        transactionRepository.save(transactionBuild)
        transactions = transactionRepository.findAllByUserId("user0001")
        assertThat(transactions.size).isEqualTo(1)
    }
}
