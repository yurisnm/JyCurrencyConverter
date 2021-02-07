package application.api.exceptions

/**
 * Exception used for BAD_REQUEST.
 */
class InvalidTransaction (
    val type: String,
    override val message: String,
): Exception(message)