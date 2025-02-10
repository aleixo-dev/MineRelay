package com.nicolas.minerelay.data.di

import com.nicolas.minerelay.data.remote.api.KtorClientFactory
import com.nicolas.minerelay.data.repository.PlayerRepositoryImpl
import com.nicolas.minerelay.domain.repository.PlayerRepository
import com.nicolas.minerelay.ui.home.HomeViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { KtorClientFactory.create(CIO.create()) }
    single<PlayerRepository> { PlayerRepositoryImpl(get()) }

    viewModel { HomeViewModel(get()) }
}