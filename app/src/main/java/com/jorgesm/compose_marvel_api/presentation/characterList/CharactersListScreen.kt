package com.jorgesm.compose_marvel_api.presentation.characterList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.jorgesm.compose_marvel_api.presentation.ui.component.CarouselCard
import com.jorgesm.compose_marvel_api.presentation.ui.component.CharacterList
import com.jorgesm.domain.model.response.CharactersResponse


@Composable
fun CharactersListScreen(
    mainViewModel: MainViewModel,
    navHostController: NavHostController
) {

    val list: CharactersResponse by mainViewModel.list.collectAsStateWithLifecycle()

    mainViewModel.getList()
//    CharacterList(list = list.result, navHostController)
    CarouselCard(list.result, navHostController)
}
