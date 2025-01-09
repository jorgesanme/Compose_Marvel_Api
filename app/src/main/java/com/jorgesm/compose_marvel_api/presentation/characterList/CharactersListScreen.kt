package com.jorgesm.compose_marvel_api.presentation.characterList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jorgesm.compose_marvel_api.presentation.ui.component.CarouselCard
import com.jorgesm.compose_marvel_api.presentation.ui.component.LoadingIndicator
import com.jorgesm.domain.model.response.CharactersResponse


@Composable
fun CharactersListScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    navigateToDetail: (Long)-> Unit
) {
    val list: CharactersResponse by mainViewModel.list.collectAsStateWithLifecycle()
    val isLoading: Boolean by mainViewModel.isLoading.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) { mainViewModel.getList() }
    if (isLoading){
        LoadingIndicator()
    }else {
        CarouselCard(list.result, navigateToDetail) { mainViewModel.setNewOffset() }
    }
}


