package com.nicolas.minerelay.ui.home

sealed class HomeUiEvent {
    data object Refresh : HomeUiEvent()
    data class Search(val query: String) : HomeUiEvent()
}