package application.api.entities

import domain.entities.Transaction
import org.joda.time.DateTime

class TransactionRequest(
    val id: Int,
    val userId: String,
    private val sourceCurrency: String,
    private val sourceValue: Float,
    private val targetCurrency: String
    ){

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
