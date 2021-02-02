package application.errors

data class HttpError(
    val type: String,
    val message: String,
)
