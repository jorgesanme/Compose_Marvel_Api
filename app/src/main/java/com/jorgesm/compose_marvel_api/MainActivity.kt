package com.jorgesm.compose_marvel_api

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jorgesm.compose_marvel_api.presentation.ui.CharacterItem
import com.jorgesm.compose_marvel_api.presentation.ui.CharacterList
import com.jorgesm.compose_marvel_api.ui.theme.Compose_Marvel_ApiTheme
import com.jorgesm.data.server.api.ApiServices
import com.jorgesm.data.server.repository.ApiServicesRepositoryImpl
import com.jorgesm.domain.model.Character
import com.jorgesm.domain.model.response.CharactersResponse
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity  : ComponentActivity() {
private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_Marvel_ApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val list: CharactersResponse by mainViewModel.list.collectAsStateWithLifecycle()
                    CharacterList(list = list.result)

                }
            }
        }
    }
}





