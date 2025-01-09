package com.jorgesm.compose_marvel_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jorgesm.compose_marvel_api.core.navigation.NavigationWrapper
import com.jorgesm.compose_marvel_api.presentation.ui.theme.Compose_Marvel_ApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_Marvel_ApiTheme {
                NavigationWrapper()
            }
        }
    }
}