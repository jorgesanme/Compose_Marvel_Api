package com.jorgesm.compose_marvel_api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jorgesm.compose_marvel_api.presentation.ui.detail.DetailView
import com.jorgesm.compose_marvel_api.presentation.ui.detail.DetailViewModel
import com.jorgesm.compose_marvel_api.presentation.ui.characterList.CharactersListScreen
import com.jorgesm.compose_marvel_api.presentation.ui.characterList.MainViewModel
import com.jorgesm.compose_marvel_api.ui.theme.Compose_Marvel_ApiTheme
import com.jorgesm.compose_marvel_api.utils.Routes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private val detailViewModel: DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_Marvel_ApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.CharactersListScreen.route
                    ) {
                        composable(Routes.CharactersListScreen.route) {
                            CharactersListScreen(
                                mainViewModel = mainViewModel, navigationController
                            )
                        }
                        composable(Routes.DetailScreen.route, arguments = listOf(navArgument("itemId"){
                            type = NavType.IntType})){backStackEntry ->
                            val itemId = backStackEntry.arguments?.getInt("itemId") ?: 0
                            DetailView(detailViewModel = detailViewModel, navHostController = navigationController, itemId = itemId)

                        }
                    }
                }
            }
        }
    }
}





