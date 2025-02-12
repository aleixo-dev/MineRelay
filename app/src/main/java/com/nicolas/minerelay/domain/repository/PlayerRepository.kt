package com.nicolas.minerelay.domain.repository

import com.nicolas.minerelay.domain.models.PlayerIdentity
import kotlinx.coroutines.flow.Flow

interface PlayerRepository {
    suspend fun getPlayers(): Flow<Result<List<PlayerIdentity>>>
    suspend fun searchPlayer(name: String): Flow<Result<List<PlayerIdentity>>>


}