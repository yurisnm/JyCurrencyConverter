package application.api.errors

/**
 * Basic error structure.
 */
data class HttpError(
    val type: String,
    val message: String,
)
