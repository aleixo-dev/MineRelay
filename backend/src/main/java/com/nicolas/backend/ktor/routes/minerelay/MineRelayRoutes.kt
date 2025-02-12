package com.nicolas.backend.ktor.routes.minerelay

import com.nicolas.backend.ktor.model.UserIdentity
import com.nicolas.backend.ktor.repository.InMemoryRepository
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.JsonConvertException
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.mineRelayRoute(inMemoryRepository: InMemoryRepository) {

    route("api/v1/mine-relay") {
        get {

            val playerName = call.request.queryParameters["name"]

            if (playerName == null) {
                val players = inMemoryRepository.allPlayer()
                call.respond(HttpStatusCode.OK, players)
                return@get
            }

            val playersMatchByName = inMemoryRepository.searchPlayer(playerName)
            if(playersMatchByName.isEmpty()) {
                call.respond(HttpStatusCode.NotFound)
                return@get
            }
            call.respond(HttpStatusCode.OK, playersMatchByName)
        }

        post {
            try {
                val player = call.receive<UserIdentity>()
                inMemoryRepository.addPlayer(player)

                call.respond(HttpStatusCode.Created)
            } catch (exception: IllegalStateException) {
                call.respond(HttpStatusCode.BadRequest)
            } catch (exception: JsonConvertException) {
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        delete("/{uuid}") {
            val uuid = call.parameters["uuid"]
            if (uuid == null) {
                call.respond(HttpStatusCode.BadRequest)
                return@delete
            }

            if (inMemoryRepository.removePlayer(uuid)) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}