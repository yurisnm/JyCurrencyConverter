package api

import application.api.controllers.TransactionController
import application.api.entities.TransactionRequest
import application.api.exceptions.InvalidTransaction
import domain.entities.Transaction
import domain.service.Service
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.eclipse.jetty.http.HttpStatus
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import repository.TransactionRequestBuilder

class TransactionControllerTest {

    private var contextMock = mockk<Context>(relaxed = true)
    private val transactionServiceMock = mockk<Service<Transaction>>()

    private val transactionRequest = TransactionRequestBuilder.build()
    private val transactionController = TransactionController(transactionServiceMock)

    private fun genTransactionRequest(transactionRequest: TransactionRequest) = transactionRequest.toModel().copy()


    @Test
    fun all_transactions(){
        every { transactionServiceMock.findAll()} returns listOf(this.genTransactionRequest(transactionRequest))
        val transactionRequest = transactionController.listAllTransactions(contextMock)

        assertThat(transactionRequest.size).isEqualTo(1)

        verify { contextMock.status(HttpStatus.OK_200) }
        verify { transactionServiceMock.findAll() }

    }

    @Test
    fun all_transactions_by_id(){
        val userId = "user0001"
        every { transactionServiceMock.findAllByUserId(any()) } returns listOf(this.genTransactionRequest(transactionRequest))
        every { contextMock.pathParam(any()) } returns userId
        val transactionRequest = transactionController.listAllTransactionsByUserId(contextMock)

        assertThat(transactionRequest.size).isEqualTo(1)

        verify { contextMock.status(HttpStatus.OK_200) }
        verify { transactionServiceMock.findAllByUserId(userId) }
    }

    @Test
    fun error_on_save_transaction(){
        every { contextMock.bodyAsClass(TransactionRequest::class.java)} throws BadRequestResponse("Couldn't deserialize body")

        assertThrows(InvalidTransaction::class.java){
            transactionController.registerTransaction(contextMock)
        }.let{
            assertThat(it.type).isEqualTo("Invalid Transaction")
        }

    }

}