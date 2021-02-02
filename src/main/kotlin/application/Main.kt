package application

import io.javalin.Javalin

fun main(args: Array<String>) {
    // TODO: A Koin component will be needed for a better App start
    // TODO: It's necessary a low level api for data-base connection to handle query and responses.
    val app = Javalin.create().start(7000)
    app.get("/") { ctx -> ctx.result("Hello World") }
}