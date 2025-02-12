package com.nicolas.minerelay.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nicolas.minerelay.R
import com.nicolas.minerelay.ui.home.components.PlayerCardInfo
import com.nicolas.minerelay.ui.home.components.SearchableTopAppBar
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = koinViewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        modifier = modifier,
        state = state,
        onRefresh = viewModel::requestPlayers,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    state: HomeUiState,
    onRefresh: () -> Unit = {},
    onEvent: (HomeUiEvent) -> Unit
) {

    val context = LocalContext.current
    val listState = rememberLazyListState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            SearchableTopAppBar(
                scrollBehavior = scrollBehavior,
                onSearch = { query ->
                    onEvent(HomeUiEvent.Search(query))
                },
                onRefresh = onRefresh
            )
        }
    ) { innerPadding ->
        when (state) {
            is HomeUiState.Error -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.message,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }

            HomeUiState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is HomeUiState.Success -> {
                LazyColumn(
                    modifier = Modifier.padding(innerPadding),
                    contentPadding = PaddingValues(horizontal = 15.dp),
                    state = listState
                ) {
                    item {
                        Text(
                            text = context.getString(R.string.online_players) + " (${state.players.size})",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    items(items = state.players, key = { it.uuid }) { player ->
                        Column {
                            Spacer(modifier = Modifier.height(10.dp))
                            PlayerCardInfo(player = player)
                        }
                    }
                }
            }
        }
    }
}