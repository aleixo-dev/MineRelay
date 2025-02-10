package com.nicolas.minerelay.ui.home

import com.nicolas.minerelay.domain.models.PlayerIdentity

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Error(val message: String) : HomeUiState()
    data class Success(
        val players: List<PlayerIdentity> = emptyList()
    ) : HomeUiState()
}
