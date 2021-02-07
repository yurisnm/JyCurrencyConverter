package resources.schemas
import org.jetbrains.exposed.sql.Table

/**
 * Table that represents the structure of a transaction.
 */
object TransactionSchema: Table() {
    val id = integer("id").autoIncrement().primaryKey()
    val userId = varchar("userId",60)
    val sourceCurrency = varchar("sourceCurrency", 3)
    val sourceValue = float("sourceValue")
    val targetCurrency = varchar("targetCurrency", 3)
    val conversionRate = float("conversionRate")
    val dateTime = datetime("dateTime")
}