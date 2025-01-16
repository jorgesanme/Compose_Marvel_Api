package com.jorgesm.compose_marvel_api.presentation.ui.component

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import com.jorgesm.compose_marvel_api.R

@Composable
fun ArrowButton(
    isEnable: Boolean,
    iconVector: ImageVector,
    contentDescription: String,
    onClick: () -> Unit
) {
    IconButton(
        enabled = isEnable,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = colorResource(R.color.marvel_red),
            disabledContentColor = colorResource(R.color.marvel_grey_opaque)
        ),
        onClick = { onClick() }

    ) {
        Icon(
            imageVector = iconVector,
            contentDescription = contentDescription
        )
    }
}