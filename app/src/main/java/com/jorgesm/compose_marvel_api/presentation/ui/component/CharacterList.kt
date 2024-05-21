package com.jorgesm.compose_marvel_api.presentation.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jorgesm.domain.model.Character

@Composable
fun CharacterList(
    list: List<Character>,
    navHostController: NavHostController
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxSize().padding(16.dp)
    ) {
        items(list, key = { it.id!! }) { character ->
            CharacterItem(item = character, navHostController = navHostController)
        }
    }

}