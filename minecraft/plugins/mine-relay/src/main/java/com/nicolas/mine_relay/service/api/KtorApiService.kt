package com.nicolas.mine_relay.service.api

import com.nicolas.mine_relay.model.UserIdentity
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.header
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class KtorApiService(val client: HttpClient?) {

    private val baseUrl = "http://localhost:3333/api/v1/mine-relay"
    private val platformHeader = "minecraft"

    suspend fun postUserIdentities(userIdentities: List<UserIdentity>) =
        withContext(Dispatchers.IO) {

            if (userIdentities.isEmpty()) return@withContext

            try {
                client?.post(baseUrl) {
                    contentType(ContentType.Application.Json)
                    headers { append("platform", platformHeader) }
                    setBody(userIdentities)
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
            }
        }

    suspend fun deletePlayerIdentity(uuid: String?) = withContext(Dispatchers.IO) {

        if (uuid == null) return@withContext

        try {
            client?.delete("$baseUrl/$uuid") {
                contentType(ContentType.Application.Json)
                headers { header("platform", "minecraft") }
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}