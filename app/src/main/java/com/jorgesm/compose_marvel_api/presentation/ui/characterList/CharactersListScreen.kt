package com.jorgesm.compose_marvel_api.presentation.ui.characterList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jorgesm.compose_marvel_api.presentation.ui.component.CharacterList
import com.jorgesm.domain.model.response.CharactersResponse


@Composable
fun CharactersListScreen(
    mainViewModel: MainViewModel,
    navHostController: NavHostController
) {
    mainViewModel.getList(0)

    val list: CharactersResponse by mainViewModel.list.collectAsStateWithLifecycle()

    CharacterList(list = list.result, navHostController)
}
