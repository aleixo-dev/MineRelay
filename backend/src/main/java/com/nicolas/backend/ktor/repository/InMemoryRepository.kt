package com.nicolas.backend.ktor.repository

import com.nicolas.backend.ktor.model.PlayerData

class InMemoryRepository {

    private val players: MutableSet<PlayerData> = mutableSetOf()

    fun allPlayer(): MutableSet<PlayerData> = players

    fun addPlayer(player: PlayerData) {
        players.add(player)
    }

    fun removePlayer(uuid: String): Boolean {
        return players.removeIf { it.uuid == uuid }
    }
}