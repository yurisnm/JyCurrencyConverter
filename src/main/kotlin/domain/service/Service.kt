package domain.service

interface Service<T> {
    fun save(entity: T): T
    fun findAll(): List<T>
    fun findAllByUserId(userId: String): List<T>
}