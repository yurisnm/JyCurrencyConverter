package application.api.entities

import domain.entities.Transaction
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

class TransactionResponse(
    val id: Int,
    val userId: String,
    val sourceCurrency: String,
    val sourceValue: Float,
    val targetCurrency: String,
    val targetValue: Float,
    val conversionRate: Float,
    val dateTime: String,
    ) {
    companion object {
        private val formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")

        fun toResponse(transaction: Transaction): TransactionResponse = TransactionResponse(
            id = transaction.id,
            userId = transaction.userId,
            sourceCurrency = transaction.sourceCurrency,
            sourceValue = transaction.sourceValue,
            targetCurrency = transaction.targetCurrency,
            targetValue = transaction.sourceValue*transaction.conversionRate,
            conversionRate = transaction.conversionRate,
            dateTime = transaction.dateTime.toString(formatter),
        )
    }
}
