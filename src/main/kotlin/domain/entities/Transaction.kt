package domain.entities

import org.joda.time.DateTime

/**
 * The transaction structure that represents an transaction operation made by an User.
 *
 * @property id[Int]: The id of the transaction used as primary key on the Data Base.
 * @property userId[String]: The id of the user that made the transaction.
 * @property sourceCurrency[String]: The currency the user entered the value.
 * @property sourceValue[Float]: The value that will be converted.
 * @property targetCurrency[String]: The currency used to convert the entered value.
 * @property conversionRate[Float]: Value used to convert sourceValue onto targetValue.
 * @property dateTime[String]: The date and time which the transaction happened.
 */
data class Transaction (
    val id: Int,
    val userId: String,
    val sourceCurrency: String,
    val sourceValue: Float,
    val targetCurrency: String,
    val conversionRate: Float,
    val dateTime: DateTime,
)