package com.nicolas.backend.ktor

import com.nicolas.backend.ktor.repository.InMemoryRepository
import com.nicolas.backend.ktor.routes.minerelay.mineRelayRoute
import com.typesafe.config.ConfigFactory
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.config.HoconApplicationConfig
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.routing.routing

fun main() {

    val config = HoconApplicationConfig(ConfigFactory.load())
    val port = config.propertyOrNull("ktor.deployment.port")?.getString()?.toInt() ?: 8080

    embeddedServer(
        Netty, port = 3333,
        host = "0.0.0.0"
    ) {
        module()
    }.start(wait = true)
}

fun Application.module() {

    install(ContentNegotiation) {
        json()
    }

    install(CORS) {
        anyHost()
    }

    routing {
        mineRelayRoute(InMemoryRepository())
    }
}