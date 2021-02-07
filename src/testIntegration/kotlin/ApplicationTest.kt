import application.Main
import application.api.entities.TransactionResponse
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.javalin.Javalin
import khttp.responses.Response
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.koin.core.context.stopKoin

/**
 * Tests each service offered by the application, they are:
 * - Insert a new transaction.
 * - Get all transactions.
 * - Get all transactions by an user id.
 */
class ApplicationTest {

    private lateinit var app: Javalin
    private val url = "http://localhost:7000/"

    @BeforeEach
    fun setUp() {
        /**
         * Raise the JyCurrencyConverter application.
         */
        app = Main.startApplication()
    }

    @AfterEach
    fun tearDown() {
        /**
         * Stops the JyCurrencyConverter application.
         */
        app.stop()
        stopKoin()
    }

    @Test
    fun testPOSTTransaction() {
        /**
         * The application must be able to save a new transaction passing an expected body.
         */
        val response = createTransaction("transaction")
        assertEquals(201, response.statusCode)

        val response2 = createTransaction("transaction_invalid")
        assertEquals(400, response2.statusCode)
    }

    @Test
    fun testGETAllTransactions() {
        /**
         * The application must be able to list all saved transactions.
         */
        createTransaction("transaction")
        val response = khttp.get(url = url + "transaction")
        val transactions = response.text.deserialize<List<TransactionResponse>>()
        assertTrue(transactions.isNotEmpty())
        assertEquals(200, response.statusCode)
    }

    @Test
    fun testGETAllTransactionsByUserId() {
        /**
         * The application must be able to list all saved transactions by userId.
         */
        createTransaction("transaction")
        val response = khttp.get(url = url + "transaction/user0001")
        val transactions = response.text.deserialize<List<TransactionResponse>>()
        assertTrue(transactions.isNotEmpty())
        assertEquals(200, response.statusCode)

        val response2 = khttp.get(url = url + "transaction/user0002")
        val transactions2 = response2.text.deserialize<List<TransactionResponse>>()
        assertTrue(transactions2.isEmpty())
        assertEquals(200, response2.statusCode)
    }

    private fun createTransaction(json_file_name: String): Response {
        /**
         * The application must be able to list all saved transactions.
         * @param json_file_name [String] The name of the file that will have its content used as body for posting a transaction.
         * @return [Response] The response returned by the khttp post.
         */
        val resr = javaClass.getResource("/$json_file_name.json").readText()
        return khttp.post(
            url = url + "transaction",
            data = resr
        )
    }

    inline fun <reified T : Any> String.deserialize(): T = jacksonObjectMapper().readValue(this)
}
