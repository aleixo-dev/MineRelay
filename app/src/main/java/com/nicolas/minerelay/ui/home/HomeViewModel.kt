package com.nicolas.minerelay.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nicolas.minerelay.domain.repository.PlayerRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val playerRepository: PlayerRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.onStart {
        requestPlayers()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        HomeUiState.Loading
    )

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.Refresh -> requestPlayers()
            is HomeUiEvent.Search -> searchPlayers(event.query)
        }
    }

    @OptIn(FlowPreview::class)
    private fun searchPlayers(query: String) = viewModelScope.launch {
        playerRepository.searchPlayer(query)
            .onStart { _uiState.update { HomeUiState.Loading } }
            .debounce(500)
            .collect { result ->
                result.fold(
                    onSuccess = { players ->
                        _uiState.value = HomeUiState.Success(players)
                    },
                    onFailure = {
                        _uiState.value = HomeUiState.Error("Couldn't find players")
                    }
                )
            }
    }

    fun requestPlayers() = viewModelScope.launch {
        playerRepository.getPlayers()
            .onStart { _uiState.update { HomeUiState.Loading } }
            .collect { result ->
                result.fold(
                    onSuccess = { players ->
                        _uiState.value = HomeUiState.Success(players)
                    },
                    onFailure = {
                        _uiState.value = HomeUiState.Error("Couldn't load players")
                    }
                )
            }
    }
}