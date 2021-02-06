package api

import application.api.errors.HandlerError
import application.api.errors.HttpError
import application.api.exceptions.InvalidTransaction
import io.javalin.http.Context
import io.mockk.mockk
import io.mockk.verify
import org.eclipse.jetty.http.HttpStatus
import org.junit.jupiter.api.Test

class ErrorTest {

    private var contextMock = mockk<Context>(relaxed = true)


    @Test
    fun invalid_transaction(){
        val invalidTransaction = InvalidTransaction(
            type = "Invalid Transaction",
            message = "Not able to process transaction."
        )
        HandlerError.handlerErrorException(invalidTransaction, contextMock)

        verify { contextMock.status(HttpStatus.BAD_REQUEST_400)}
        verify { contextMock.json(HttpError(invalidTransaction.type, invalidTransaction.message))}
    }

    @Test
    fun server_error(){
        val serverError = Exception("Unknown error")
        HandlerError.handlerErrorException(serverError, contextMock)

        verify { contextMock.status(HttpStatus.INTERNAL_SERVER_ERROR_500)}
        verify { contextMock.json(HttpError("Unknown error", "error not identified"))}
    }
}