package com.jorgesm.compose_marvel_api.presentation.characterList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jorgesm.compose_marvel_api.presentation.ui.component.CarouselCard
import com.jorgesm.compose_marvel_api.presentation.ui.component.LoadingIndicator


@Composable
fun CharactersListScreen(
    mainViewModel: MainViewModel = hiltViewModel(),
    navigateToDetail: (Long) -> Unit
) {
    val list by mainViewModel.list.collectAsStateWithLifecycle()
    val isLoading: Boolean by mainViewModel.isLoading.collectAsStateWithLifecycle()
    val isPreviousArrowEnable: Boolean by mainViewModel.isPreviousArrowEnable.collectAsStateWithLifecycle()
    val counter: Int by mainViewModel.dbCounter.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit) {
        if (counter == 0)
            mainViewModel.getDDBBCount()
            mainViewModel.getLocalDataList()
    }

    if (isLoading) {
        LoadingIndicator()
    } else {
        CarouselCard(
            list = list,
            isPreviousArrowEnable = isPreviousArrowEnable,
            navigateToDetail = navigateToDetail,
            searchPreviousList = { mainViewModel.searchPreviousList() },
            searchNewList = { mainViewModel.searchNextList() }
        )
    }
}


