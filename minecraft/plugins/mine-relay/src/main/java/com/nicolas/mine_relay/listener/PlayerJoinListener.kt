package com.nicolas.mine_relay.listener

import com.nicolas.mine_relay.model.UserIdentity
import com.nicolas.mine_relay.service.api.KtorApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerJoinListener(private val client: KtorApiService?) : Listener {

    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        coroutineScope.launch {
            client?.postUserIdentity(
                UserIdentity(
                    name = event.player.name,
                    uuid = event.player.uniqueId.toString()
                )
            )
        }
    }
}