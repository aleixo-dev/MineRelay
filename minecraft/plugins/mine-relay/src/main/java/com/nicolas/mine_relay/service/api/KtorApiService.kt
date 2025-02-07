package com.nicolas.mine_relay.service.api

import com.nicolas.mine_relay.model.UserIdentity
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KtorApiService(val client: HttpClient?) {

    suspend fun postUserIdentities(players: List<UserIdentity>) = withContext(Dispatchers.IO) {

        if (players.isEmpty()) return@withContext

        try {
            client?.post("http://localhost:8080/api/v1/mine-relay") {
                contentType(ContentType.Application.Json)
                setBody(players)
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    suspend fun deletePlayerIdentity(uuid: String?) = withContext(Dispatchers.IO) {

        if (uuid == null) return@withContext

        try {
            client?.delete("http://localhost:8080/api/v1/mine-relay/$uuid") {
            }.apply(::println)

        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}