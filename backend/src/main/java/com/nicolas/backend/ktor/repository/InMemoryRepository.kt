package com.nicolas.backend.ktor.repository

import com.nicolas.backend.ktor.model.UserIdentity

class InMemoryRepository {

    private val players: MutableSet<List<UserIdentity>> = mutableSetOf()

    fun allPlayer(): MutableSet<List<UserIdentity>> = players

    fun addPlayer(player: List<UserIdentity>) {
        players.add(player)
    }

    fun removePlayer(uuid: String): Boolean {
        return players.removeIf { player -> player.any { it.uuid == uuid } }
    }
}