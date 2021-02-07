package resources.extensions

import domain.entities.Transaction
import org.jetbrains.exposed.sql.ResultRow
import resources.schemas.TransactionSchema

/**
 * Maps schema fields to transaction properties.
 */
fun ResultRow.toTransaction(): Transaction = Transaction(
    id = get(TransactionSchema.id),
    userId = get(TransactionSchema.userId),
    sourceCurrency = get(TransactionSchema.sourceCurrency),
    sourceValue = get(TransactionSchema.sourceValue),
    targetCurrency = get(TransactionSchema.targetCurrency),
    conversionRate = get(TransactionSchema.conversionRate),
    dateTime = get(TransactionSchema.dateTime),
)
