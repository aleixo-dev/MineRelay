package com.nicolas.backend.ktor.repository

import com.nicolas.backend.ktor.model.UserIdentity

class InMemoryRepository {

    private val players: MutableList<UserIdentity> = mutableListOf()

    fun allPlayer() = players.sortedBy { it.name }

    fun addPlayer(player: UserIdentity) {
        players.add(player)
    }

    fun removePlayer(uuid: String): Boolean {
        return players.removeIf { it.uuid == uuid }
    }

    fun searchPlayer(name: String): List<UserIdentity> {
        return players.filter { it.name.startsWith(name, ignoreCase = true) }
    }
}