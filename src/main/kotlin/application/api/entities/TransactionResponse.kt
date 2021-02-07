package application.api.entities

import domain.entities.Transaction
import org.joda.time.format.DateTimeFormat

/**
 * The transaction response after a successful transaction is registered.
 *
 * @property id[Int]: The id of the transaction used as primary key on the Data Base.
 * @property userId[String]: The id of the user that made the transaction.
 * @property sourceCurrency[String]: The currency the user entered the value.
 * @property sourceValue[Float]: The value that will be converted.
 * @property targetCurrency[String]: The currency used to convert the entered value.
 * @property targetValue[Float]: The value of sourceValue after conversion using the conversionRate.
 * @property conversionRate[Float]: Value used to convert sourceValue onto targetValue.
 * @property dateTime[String]: The date and time which the transaction happened.
 */
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
