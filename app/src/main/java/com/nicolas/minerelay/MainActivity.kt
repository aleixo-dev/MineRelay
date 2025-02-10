package com.nicolas.minerelay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nicolas.minerelay.ui.home.HomeRoute
import com.nicolas.minerelay.ui.route.Home
import com.nicolas.minerelay.ui.theme.MineRelayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MineRelayTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                ) { innerPadding ->
                    NavHost(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
private fun NavHost(modifier: Modifier) {

    val navController = rememberNavController()

    NavHost(modifier = modifier, navController = navController, startDestination = Home) {
        composable<Home> {
            HomeRoute()
        }
    }
}