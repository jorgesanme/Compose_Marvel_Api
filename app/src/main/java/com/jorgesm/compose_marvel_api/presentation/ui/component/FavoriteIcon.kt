package com.jorgesm.compose_marvel_api.presentation.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FavoriteIcon(isFavorite: Boolean, modifier: Modifier) {
    Box(
        modifier = modifier
            .padding(end = 8.dp)
            .size(40.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isFavorite)
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite Icon",
                tint = Color.Red
            )
        else
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = "Favorite Icon",
                tint = Color.Red
            )
    }
}