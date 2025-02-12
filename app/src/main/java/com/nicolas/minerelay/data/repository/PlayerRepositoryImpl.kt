package com.nicolas.minerelay.data.repository

import com.nicolas.minerelay.BuildConfig
import com.nicolas.minerelay.data.mappers.toPlayerIdentity
import com.nicolas.minerelay.data.remote.response.PlayerIdentityDto
import com.nicolas.minerelay.domain.models.PlayerIdentity
import com.nicolas.minerelay.domain.repository.PlayerRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class PlayerRepositoryImpl(
    private val client: HttpClient,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PlayerRepository {

    override suspend fun getPlayers(): Flow<Result<List<PlayerIdentity>>> =
        flow<Result<List<PlayerIdentity>>> {
            runCatching {
                val playerResponse: List<PlayerIdentityDto> =
                    client.get(
                        "${BuildConfig.BASE_URL}${BuildConfig.BASE_ENDPOINT}"
                    ).body()

                emit(Result.success(playerResponse.toPlayerIdentity()))
            }.onFailure { exception ->
                emit(Result.failure(exception))
            }
        }.flowOn(dispatcher)

    override suspend fun searchPlayer(name: String): Flow<Result<List<PlayerIdentity>>> =
        flow {
            runCatching {
                val playerResponse: List<PlayerIdentityDto> =
                    client.get(
                        "${BuildConfig.BASE_URL}${BuildConfig.BASE_ENDPOINT}?name=$name"
                    ).body()

                emit(Result.success(playerResponse.toPlayerIdentity()))
            }.onFailure { exception ->
                emit(Result.failure(exception))
            }
        }
}