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

class ApplicationTest {

    private lateinit var app: Javalin
    private val url = "http://localhost:7000/"

    @BeforeEach
    fun setUp(){
        app = Main.startApplication()
    }

    @AfterEach
    fun tearDown(){
        app.stop()
        stopKoin()
    }

    @Test
    fun testPOSTTransaction(){
        val response = createTransaction()
        assertEquals(201, response.statusCode)
    }

    @Test
    fun testGETAllTransactions(){
        createTransaction()
        val response = khttp.get(url = url + "transaction")
        val transactions = response.text.deserialize<List<TransactionResponse>>()
        assertTrue(transactions.isNotEmpty())
        assertEquals(200,response.statusCode)
    }

    @Test
    fun testGETAllTransactionsByUserId(){

    }

    private fun createTransaction(): Response {
        val resr = javaClass.getResource("/transaction.json").readText()
        return khttp.post(
            url = url + "transaction",
            data = resr
        )
    }
    inline fun <reified T : Any> String.deserialize(): T = jacksonObjectMapper().readValue(this)
}