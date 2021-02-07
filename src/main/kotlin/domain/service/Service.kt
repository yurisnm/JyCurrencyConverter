package domain.service

/**
 * All offered services.
 */
interface Service<T> {

    /**
     * Save a new transaction made by an User.
     */
    fun save(entity: T): T

    /**
     * List all transactions made by all Users.
     */
    fun findAll(): List<T>

    /**
     * List all transactions made by certain User.
     */
    fun findAllByUserId(userId: String): List<T>
}