package application.api.entities

import domain.entities.Transaction
import org.joda.time.DateTime

/**
 * The transaction request used to ask for a new transaction registering.
 *
 * @property id[Int]: The id of the transaction used as primary key on the Data Base.
 * @property userId[String]: The id of the user that made the transaction.
 * @property sourceCurrency[String]: The currency the user entered the value.
 * @property sourceValue[Float]: The value that will be converted.
 * @property targetCurrency[String]: The currency used to convert the entered value.
 */
class TransactionRequest(
    val id: Int,
    val userId: String,
    private val sourceCurrency: String,
    private val sourceValue: Float,
    private val targetCurrency: String
) {

    private val converter = TransactionRateConverter()

    fun toModel(): Transaction = Transaction(
        id = this.id,
        userId = this.userId,
        sourceCurrency = this.sourceCurrency,
        sourceValue = this.sourceValue,
        targetCurrency = this.targetCurrency,
        conversionRate = converter.getConversionRate(this.sourceCurrency, this.targetCurrency),
        dateTime = DateTime.now()
    )
}
