package repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import resources.repositores.TransactionRepository

/**
 * Test
 */
class TransactionRepositoryTest: RepositoryTestSetup() {

    private val transactionBuild = TransactionRequestBuilder.build().toModel()
    private val transactionRepository = TransactionRepository()

    @Test
    fun transaction_save(){
        /**
         *
         */
        val transaction = transactionRepository.save(transactionBuild)
        assertNotNull(transaction)
    }

    @Test
    fun transaction_all(){

        var transactions = transactionRepository.findAll()
        assertThat(transactions.size).isEqualTo(0)
        transactionRepository.save(transactionBuild)

        transactions = transactionRepository.findAll()
        assertThat(transactions.size).isEqualTo(1)

    }

    @Test
    fun transaction_all_by_user_id(){
        var transactions = transactionRepository.findAllByUserId("user0001")
        assertThat(transactions.size).isEqualTo(0)

        transactionRepository.save(transactionBuild)
        transactions = transactionRepository.findAllByUserId("user0001")
        assertThat(transactions.size).isEqualTo(1)

    }

}