package com.jorgesm.compose_marvel_api.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jorgesm.compose_marvel_api.presentation.characterList.CharactersListScreen
import com.jorgesm.compose_marvel_api.presentation.detail.DetailView

@Composable
fun NavigationWrapper() {
    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = MainScreen
    ) {
        composable<MainScreen> {
            CharactersListScreen(
                navigateToDetail = { itemId ->navigationController.navigate( DetailScreen(itemId=itemId)) }
            )
        }
        composable<DetailScreen>{backStackEntry ->
            val itemId = backStackEntry.toRoute<DetailScreen>().itemId
            DetailView( onBackPressed = {navigationController.popBackStack()}, itemId = itemId)
        }

    }
}