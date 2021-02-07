package application.api.config

import application.api.controllers.TransactionController
import domain.entities.Transaction
import domain.repo.Repository
import domain.service.Service
import domain.service.TransactionService
import org.koin.core.qualifier.named
import org.koin.dsl.module
import resources.repositores.TransactionRepository

/**
 * All modules and dependency injections that are used by the application.
 */
val modulesAll = module {

    single<Repository<Transaction>>(named("transaction")) { TransactionRepository() }

    single<Service<Transaction>> { TransactionService(get(named("transaction"))) }

    single { TransactionController(get()) }
}
