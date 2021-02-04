package application.api.errors

data class HttpError(
    val type: String,
    val message: String,
)
