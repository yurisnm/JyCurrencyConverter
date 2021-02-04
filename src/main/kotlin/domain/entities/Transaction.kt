package domain.entities

import org.joda.time.DateTime

data class Transaction (
    val id: Int,
    val userId: String,
    val sourceCurrency: String,
    val sourceValue: Float,
    val targetCurrency: String,
    val conversionRate: Float,
    val dateTime: DateTime,
)