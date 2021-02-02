package application.entities

data class TransactionResponse(
    val id: Int,
    val userId: String,
    val sourceCurrency: String,
    val sourceValue: Float,
    val targetCurrency: String,
    val targetValue: Float,
    val rateConversion: Float,
    val dateTime: String,
    ) {
    // TODO: implement the toResponse from a domain/Transaction to a application/TransactionResponse
}
