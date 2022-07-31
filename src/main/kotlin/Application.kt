import io.ktor.server.application.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {

    routing {
        get("/haha"){
            val response = "keke"
            call.respond(response)
        }
    }
}