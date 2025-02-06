package com.nicolas.backend.ktor.routes.minerelay

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route

fun Route.mineRelayRoute() {

    route("api/v1/mine-relay") {
        get {
            call.respond(HttpStatusCode.OK, "Ok!")
        }
    }
}