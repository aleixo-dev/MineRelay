package com.nicolas.minerelay.ui.home.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nicolas.minerelay.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchableTopAppBar(
    onSearch: (String) -> Unit,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null
) {

    var isSearchActive by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    TopAppBar(
        scrollBehavior = scrollBehavior,
        modifier = modifier,
        windowInsets = WindowInsets(0, 0, 0, 0),
        title = {
            AnimatedContent(
                targetState = isSearchActive,
                label = "Search",
                transitionSpec = {
                    fadeIn(animationSpec = tween(durationMillis = 800)) togetherWith fadeOut(
                        animationSpec = tween(800)
                    )
                },
            ) { targetState ->
                if (targetState) {
                    TextField(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(end = 20.dp),
                        value = searchQuery,
                        onValueChange = { query ->
                            searchQuery = query
                            onSearch(query)
                        },
                        placeholder = {
                            Text(
                                text = stringResource(R.string.placeholder_search_player),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.5f
                                )
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = {
                                if (searchQuery.isNotEmpty()) {
                                    searchQuery = ""
                                    onSearch("")
                                } else {
                                    isSearchActive = false
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Close Search"
                                )
                            }
                        },
                        textStyle = TextStyle.Default.copy(
                            fontSize = MaterialTheme.typography.titleMedium.fontSize
                        ),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
                            cursorColor = MaterialTheme.colorScheme.onSurface,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        shape = MaterialTheme.shapes.extraLarge,
                        singleLine = true
                    )
                } else {
                    Text(
                        stringResource(R.string.app_name),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        },
        actions = {
            if (!isSearchActive) {
                Row {
                    IconButton(onClick = { isSearchActive = true }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    }
                    IconButton(onClick = { onRefresh() }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Refresh"
                        )
                    }
                }
            }
        },
    )
}