package application.api.controllers

import application.api.entities.TransactionRequest
import application.api.entities.TransactionResponse
import application.api.exceptions.InvalidTransaction
import domain.entities.Transaction
import domain.service.Service
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.http.BadRequestResponse
import io.javalin.http.Context
import org.eclipse.jetty.http.HttpStatus
import org.slf4j.LoggerFactory

class TransactionController(
    private val transactionService: Service<Transaction>
) {

    private val logger = LoggerFactory.getLogger(TransactionController::class.java)

    fun router(){
        get("/") { ctx ->
            ctx.status(200).result("What's up CURCONV")
        }

        path("/transaction"){
            post {ctx -> ctx.json(registerTransaction(ctx))}
            get{ctx -> ctx.json(listAllTransactions(ctx))}
        }

        path("/transaction/:userId"){
            get { ctx -> ctx.json(listAllTransactionsByUserId(ctx))}
        }
    }

    fun registerTransaction(ctx: Context): TransactionResponse = try{
        ctx.bodyAsClass(TransactionRequest::class.java).let{
            logger.info("Saving transaction with id ${it.id}")
            ctx.status(HttpStatus.CREATED_201)
            TransactionResponse.toResponse(transactionService.save(it.toModel()))
        }
    } catch (ex: BadRequestResponse){
        logger.error(ex.toString())
        throw InvalidTransaction(
            type = "Invalid Transaction",
            message = ex.message.toString()
        )
    }

    fun listAllTransactions(ctx: Context): List<TransactionResponse>{
        logger.info("Finding all Transactions")
        return transactionService.findAll().map{TransactionResponse.toResponse(it)}.also {
            ctx.status(HttpStatus.OK_200)
        }
    }

    fun listAllTransactionsByUserId(ctx: Context): List<TransactionResponse>{
        logger.info("Finding all Transactions by UserId")
        val userId = ctx.pathParam("userId")
        return transactionService.findAllByUserId(userId).map { TransactionResponse.toResponse(it) }
            .also {
                ctx.status(HttpStatus.OK_200)
            }
    }
}