package com.jorgesm.compose_marvel_api.presentation.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.jorgesm.compose_marvel_api.R

@Composable
fun FavoriteIcon(isFavorite: Boolean, modifier: Modifier, isDetails: Boolean, iconSize: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        if (isFavorite)
            Icon(
                modifier = iconSize,
                imageVector = Icons.Default.Favorite,
                contentDescription = stringResource(R.string.favorite_icon_content_description),
                tint = colorResource( R.color.marvel_red )
            )
        else {
            if (isDetails)
                Icon(
                    modifier = iconSize,
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = stringResource(R.string.favorite_icon_content_description),
                    tint = colorResource(R.color.marvel_red_opaque)
                )
        }
    }
}