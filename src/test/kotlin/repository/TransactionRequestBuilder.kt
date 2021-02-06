package repository

import application.api.entities.TransactionRequest

class TransactionRequestBuilder {

    private val id: Int = 1
    private val userId: String = "user0001"
    private val sourceCurrency: String = "USD"
    private val sourceValue: Float = 1.00F
    private val targetCurrency: String = "BRL"


    companion object{
        fun build(transaction: TransactionRequestBuilder = TransactionRequestBuilder()): TransactionRequest =
            TransactionRequest(
                id = transaction.id,
                userId = transaction.userId,
                sourceCurrency = transaction.sourceCurrency,
                sourceValue = transaction.sourceValue,
                targetCurrency = transaction.targetCurrency
            )
    }
}