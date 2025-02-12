package com.nicolas.mine_relay.plugin

import com.nicolas.mine_relay.listener.PlayerJoinListener
import com.nicolas.mine_relay.listener.PlayerQuitListener
import com.nicolas.mine_relay.service.api.KtorApiService
import com.nicolas.mine_relay.service.extensions.setupKtorClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask

class MineRelayPlugin : JavaPlugin() {

    private var ktorApiService: KtorApiService? = null
    private val pluginScope = CoroutineScope(Dispatchers.IO + Job())
    private var bukkitTask: BukkitTask? = null

    override fun onEnable() {
        saveDefaultConfig()

        ktorApiService = KtorApiService(HttpClient(CIO) {
            run {
                setupKtorClient()
            }
        })

        Bukkit.getPluginManager().registerEvents(PlayerQuitListener(ktorApiService), this)
        Bukkit.getPluginManager().registerEvents(PlayerJoinListener(ktorApiService), this)
    }

    override fun onDisable() {
        ktorApiService?.client?.close()
        pluginScope.cancel()
        bukkitTask?.cancel()
    }
}