package domain.repo

/**
 * All operations the repository will be able to do.
 * - Save a new transaction made by an User.
 * - List all transactions made by all Users.
 * - List all transactions made by certain User.
 */
interface Repository<T> {
    fun save(entity: T): T
    fun findAll(): List<T>
    fun findAllByUserId(userId: String): List<T>
}
