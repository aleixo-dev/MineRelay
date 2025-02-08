package com.nicolas.mine_relay.listener

import com.nicolas.mine_relay.service.api.KtorApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class PlayerQuitListener(private val client: KtorApiService?) : Listener {

    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

    @EventHandler
    private fun onPlayerQuit(event: PlayerQuitEvent) {
        coroutineScope.launch {
            client?.deletePlayerIdentity(event.player.uniqueId.toString())
        }
    }
}