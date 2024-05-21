package com.jorgesm.compose_marvel_api.presentation.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BadBoxCounter(counter: String, imageVector: ImageVector) {
    if (counter != null) {
        BadgedBox(
            badge = {
                Text(
                    text = counter,
                    color = Color.LightGray,
                    modifier = Modifier.padding(start = 8.dp)
                )
            },
            modifier = Modifier.padding(24.dp),
        ) {
            Icon(imageVector = imageVector, contentDescription = "", tint = Color.DarkGray)
        }
    }
}

@Preview
@Composable
fun PreviewBadgedBox() {
    BadBoxCounter(counter = "10", Icons.Rounded.AccountCircle)
}