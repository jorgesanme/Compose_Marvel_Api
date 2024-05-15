package com.jorgesm.compose_marvel_api.presentation.ui

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.jorgesm.domain.model.Character

@Composable
fun CharacterList(
    list: List<Character>
) {
    LazyColumn() {
        items(list, key = { it.id!! }){ character ->
            CharacterItem(item = character)
        }
    }

}